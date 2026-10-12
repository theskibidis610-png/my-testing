package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class AutoMend extends Module {
    private final BooleanSetting autoSwitch = addSetting(new BooleanSetting("Auto Switch", "Switch to XP bottles automatically", true));
    private final NumberSetting durabilityThreshold = addSetting(new NumberSetting("Durability %", "Mend below this durability percentage", 50.0, 5.0, 95.0, 5.0));

    private int previousSlot = -1;

    public AutoMend() {
        super("AutoMend", "Hold XP bottles to mend armor", Category.PLAYER);
    }

    @Override
    protected void onDisable() {
        if (mc.player != null && previousSlot != -1) {
            mc.player.getInventory().selectedSlot = previousSlot;
            previousSlot = -1;
        }
        if (mc.player != null) {
            mc.options.useKey.setPressed(false);
        }
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.interactionManager == null) return;

        // Check if any armor piece needs mending
        boolean needsMend = false;
        for (int i = 0; i < 4; i++) {
            ItemStack armor = mc.player.getInventory().getArmorStack(i);
            if (!armor.isEmpty() && armor.isDamageable()) {
                double durability = 1.0 - ((double) armor.getDamage() / armor.getMaxDamage());
                if (durability * 100.0 < durabilityThreshold.getValue()) {
                    needsMend = true;
                    break;
                }
            }
        }

        if (!needsMend) {
            if (previousSlot != -1) {
                mc.options.useKey.setPressed(false);
                mc.player.getInventory().selectedSlot = previousSlot;
                previousSlot = -1;
            }
            return;
        }

        // Already holding XP bottles
        if (mc.player.getMainHandStack().getItem() == Items.EXPERIENCE_BOTTLE) {
            mc.options.useKey.setPressed(true);
            return;
        }

        // Find and switch to XP bottles if auto switch is on
        if (autoSwitch.getValue()) {
            for (int i = 0; i < 9; i++) {
                if (mc.player.getInventory().getStack(i).getItem() == Items.EXPERIENCE_BOTTLE) {
                    if (previousSlot == -1) {
                        previousSlot = mc.player.getInventory().selectedSlot;
                    }
                    mc.player.getInventory().selectedSlot = i;
                    mc.options.useKey.setPressed(true);
                    return;
                }
            }
        }
    }
}
