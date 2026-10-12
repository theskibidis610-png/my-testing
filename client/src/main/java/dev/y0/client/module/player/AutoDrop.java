package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

public class AutoDrop extends Module {
    private final BooleanSetting dropDirt = addSetting(new BooleanSetting("Drop Dirt", "Auto drop dirt blocks", true));
    private final BooleanSetting dropCobble = addSetting(new BooleanSetting("Drop Cobblestone", "Auto drop cobblestone", true));
    private final BooleanSetting dropGravel = addSetting(new BooleanSetting("Drop Gravel", "Auto drop gravel", true));

    public AutoDrop() {
        super("AutoDrop", "Automatically drops unwanted items", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.interactionManager == null) return;

        for (int i = 0; i < 36; i++) {
            ItemStack stack = mc.player.getInventory().getStack(i);
            if (stack.isEmpty()) continue;

            boolean shouldDrop = false;
            if (dropDirt.getValue() && stack.getItem() == Items.DIRT) shouldDrop = true;
            if (dropCobble.getValue() && stack.getItem() == Items.COBBLESTONE) shouldDrop = true;
            if (dropGravel.getValue() && stack.getItem() == Items.GRAVEL) shouldDrop = true;

            if (shouldDrop) {
                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, i < 9 ? i + 36 : i, 1, SlotActionType.THROW, mc.player);
                return;
            }
        }
    }
}
