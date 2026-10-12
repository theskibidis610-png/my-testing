package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class AutoProjectile extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Ticks between throws", 10.0, 1.0, 40.0, 1.0));

    private int tickCounter;

    public AutoProjectile() {
        super("AutoProjectile", "Auto throws projectiles at nearby players", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        tickCounter++;
        if (tickCounter < (int) delay.getValue()) return;

        boolean hasTarget = false;
        for (Entity entity : mc.world.getEntities()) {
            if (entity instanceof PlayerEntity player && player != mc.player) {
                if (mc.player.distanceTo(player) < 10) {
                    hasTarget = true;
                    break;
                }
            }
        }
        if (!hasTarget) return;

        for (int i = 0; i < 9; i++) {
            var item = mc.player.getInventory().getStack(i).getItem();
            if (item == Items.SNOWBALL || item == Items.EGG || item == Items.ENDER_PEARL) {
                int oldSlot = mc.player.getInventory().selectedSlot;
                mc.player.getInventory().selectedSlot = i;
                mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
                mc.player.getInventory().selectedSlot = oldSlot;
                tickCounter = 0;
                return;
            }
        }
    }
}
