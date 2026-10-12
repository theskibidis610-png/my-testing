package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.SlotActionType;

public class Replenish extends Module {
    private final NumberSetting threshold = addSetting(new NumberSetting("Threshold", "Minimum stack count before refilling", 16.0, 1.0, 32.0, 1.0));

    public Replenish() {
        super("Replenish", "Auto refills hotbar stacks from inventory", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.currentScreen != null) return;

        for (int hotbar = 0; hotbar < 9; hotbar++) {
            ItemStack stack = mc.player.getInventory().getStack(hotbar);
            if (stack.isEmpty() || !stack.isStackable()) continue;
            if (stack.getCount() > (int) threshold.getValue()) continue;

            for (int inv = 9; inv < 36; inv++) {
                ItemStack invStack = mc.player.getInventory().getStack(inv);
                if (ItemStack.areItemsEqual(stack, invStack)) {
                    mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId,
                            inv, 0, SlotActionType.QUICK_MOVE, mc.player);
                    return;
                }
            }
        }
    }
}
