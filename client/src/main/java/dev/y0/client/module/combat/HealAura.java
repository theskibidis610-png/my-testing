package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class HealAura extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Heal range", 5.0, 1.0, 10.0, 0.5));

    public HealAura() {
        super("HealAura", "Throw healing potions at allies", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        for (PlayerEntity p : mc.world.getPlayers()) {
            if (p == mc.player) continue;
            if (p.getHealth() < 10.0f && mc.player.distanceTo(p) < range.getValue()) {
                if (mc.player.getMainHandStack().getItem() == Items.SPLASH_POTION) {
                    mc.player.setYaw((float) Math.toDegrees(Math.atan2(p.getZ() - mc.player.getZ(), p.getX() - mc.player.getX())) - 90);
                    mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
                }
                return;
            }
        }
    }
}
