package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.Hand;

public class Blocker extends Module {
    private final BooleanSetting smartBlock = addSetting(new BooleanSetting("Smart Block", "Only block when targeted by a nearby player", true));

    public Blocker() {
        super("Blocker", "Automatically blocks with shield", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        boolean hasShield = mc.player.getOffHandStack().getItem() instanceof ShieldItem
                || mc.player.getMainHandStack().getItem() instanceof ShieldItem;
        if (!hasShield) return;

        if (smartBlock.getValue()) {
            boolean threatened = false;
            for (Entity entity : mc.world.getEntities()) {
                if (entity instanceof PlayerEntity player && player != mc.player) {
                    if (mc.player.distanceTo(player) < 4.0) {
                        threatened = true;
                        break;
                    }
                }
            }
            if (!threatened) return;
        }

        if (!mc.player.isBlocking()) {
            mc.interactionManager.interactItem(mc.player, Hand.OFF_HAND);
        }
    }
}
