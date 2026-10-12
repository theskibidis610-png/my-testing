package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class AutoEat extends Module {
    private final NumberSetting hungerThreshold = addSetting(new NumberSetting("Hunger Threshold", "Start eating below this hunger level", 14.0, 1.0, 18.0, 1.0));

    private boolean wasEating;
    private int previousSlot = -1;

    public AutoEat() {
        super("AutoEat", "Automatically eat food when hungry", Category.PLAYER);
    }

    @Override
    protected void onDisable() {
        if (mc.player != null && wasEating) {
            mc.options.useKey.setPressed(false);
            wasEating = false;
        }
        if (mc.player != null && previousSlot != -1) {
            mc.player.getInventory().selectedSlot = previousSlot;
            previousSlot = -1;
        }
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.interactionManager == null) return;

        int hunger = mc.player.getHungerManager().getFoodLevel();

        if (hunger >= hungerThreshold.getValue()) {
            if (wasEating) {
                mc.options.useKey.setPressed(false);
                wasEating = false;
                if (previousSlot != -1) {
                    mc.player.getInventory().selectedSlot = previousSlot;
                    previousSlot = -1;
                }
            }
            return;
        }

        // Check if current held item is food
        ItemStack mainHand = mc.player.getMainHandStack();
        if (mainHand.getItem().getFoodComponent() != null) {
            mc.options.useKey.setPressed(true);
            wasEating = true;
            return;
        }

        // Find food in hotbar and switch to it
        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.player.getInventory().getStack(i);
            if (stack.getItem().getFoodComponent() != null) {
                if (previousSlot == -1) {
                    previousSlot = mc.player.getInventory().selectedSlot;
                }
                mc.player.getInventory().selectedSlot = i;
                mc.options.useKey.setPressed(true);
                wasEating = true;
                return;
            }
        }
    }
}
