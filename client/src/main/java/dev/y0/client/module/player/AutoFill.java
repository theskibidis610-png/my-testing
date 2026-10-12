package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.SlotActionType;

public class AutoFill extends Module {
    private final NumberSetting threshold = addSetting(new NumberSetting("Threshold", "Refill when stack count is below this", 5.0, 1.0, 32.0, 1.0));

    public AutoFill() {
        super("AutoFill", "Automatically refills hotbar slots from inventory", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.interactionManager == null) return;
        if (mc.player.currentScreenHandler != mc.player.playerScreenHandler) return;

        for (int hotbar = 0; hotbar < 9; hotbar++) {
            ItemStack hotbarStack = mc.player.getInventory().getStack(hotbar);
            if (hotbarStack.isEmpty() || hotbarStack.getCount() >= (int) threshold.getValue()) continue;
            if (hotbarStack.getMaxCount() <= 1) continue;

            // Search inventory for matching item
            for (int inv = 9; inv < 36; inv++) {
                ItemStack invStack = mc.player.getInventory().getStack(inv);
                if (!invStack.isEmpty() && ItemStack.areItemsEqual(hotbarStack, invStack)) {
                    mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, inv, 0, SlotActionType.PICKUP, mc.player);
                    mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, hotbar + 36, 0, SlotActionType.PICKUP, mc.player);
                    mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, inv, 0, SlotActionType.PICKUP, mc.player);
                    return;
                }
            }
        }
    }
}
