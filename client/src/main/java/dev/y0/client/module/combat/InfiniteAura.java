package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.util.Hand;

public class InfiniteAura extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Attack range", 10.0, 3.0, 100.0, 1.0));

    public InfiniteAura() {
        super("InfiniteAura", "Long range attack using packets", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        for (Entity entity : mc.world.getEntities()) {
            if (entity == mc.player) continue;
            if (!(entity instanceof LivingEntity living)) continue;
            if (living.isDead() || living.getHealth() <= 0) continue;
            if (!(entity instanceof PlayerEntity)) continue;

            double dist = mc.player.distanceTo(entity);
            if (dist > range.getValue()) continue;

            mc.player.networkHandler.sendPacket(PlayerInteractEntityC2SPacket.attack(entity, mc.player.isSneaking()));
            mc.player.swingHand(Hand.MAIN_HAND);
            break;
        }
    }
}
