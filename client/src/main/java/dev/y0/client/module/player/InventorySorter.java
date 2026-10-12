package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.SlotActionType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InventorySorter extends Module {
    private final ModeSetting sortType = addSetting(new ModeSetting("Sort", "Sorting method", "Name", "Name", "Category", "Quantity"));

    private boolean hasSorted;

    public InventorySorter() {
        super("InventorySorter", "Sort inventory automatically", Category.PLAYER);
    }

    @Override
    protected void onEnable() {
        hasSorted = false;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.interactionManager == null) return;
        if (mc.currentScreen != null) return;
        if (hasSorted) return;

        // Collect non-empty inventory slots (9-35 are main inventory)
        List<SlotInfo> slots = new ArrayList<>();
        for (int i = 9; i < 36; i++) {
            ItemStack stack = mc.player.getInventory().getStack(i);
            if (!stack.isEmpty()) {
                slots.add(new SlotInfo(i, stack));
            }
        }

        // Sort based on the selected mode
        Comparator<SlotInfo> comparator = switch (sortType.getValue()) {
            case "Name" -> Comparator.comparing(s -> s.stack.getName().getString());
            case "Quantity" -> Comparator.comparingInt((SlotInfo s) -> s.stack.getCount()).reversed();
            case "Category" -> Comparator.comparing((SlotInfo s) -> s.stack.getItem().getClass().getSimpleName());
            default -> Comparator.comparing(s -> s.stack.getName().getString());
        };

        slots.sort(comparator);

        // Rearrange by swapping slots to match sorted order
        int syncId = mc.player.currentScreenHandler.syncId;
        for (int targetIdx = 0; targetIdx < slots.size(); targetIdx++) {
            int targetSlot = 9 + targetIdx;
            SlotInfo info = slots.get(targetIdx);

            if (info.slot != targetSlot) {
                // Pick up from source slot
                mc.interactionManager.clickSlot(syncId, info.slot, 0, SlotActionType.PICKUP, mc.player);
                // Place in target slot
                mc.interactionManager.clickSlot(syncId, targetSlot, 0, SlotActionType.PICKUP, mc.player);
                // Put back what was in target slot
                mc.interactionManager.clickSlot(syncId, info.slot, 0, SlotActionType.PICKUP, mc.player);

                // Update slot references for remaining items
                for (int j = targetIdx + 1; j < slots.size(); j++) {
                    if (slots.get(j).slot == targetSlot) {
                        slots.get(j).slot = info.slot;
                        break;
                    }
                }
            }
        }

        hasSorted = true;
    }

    private static class SlotInfo {
        int slot;
        final ItemStack stack;

        SlotInfo(int slot, ItemStack stack) {
            this.slot = slot;
            this.stack = stack.copy();
        }
    }
}
