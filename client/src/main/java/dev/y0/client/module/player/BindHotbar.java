package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class BindHotbar extends Module {
    private final ModeSetting slot = addSetting(new ModeSetting("Slot", "Saved hotbar layout slot", "1", "1", "2", "3"));

    private final ItemStack[][] savedHotbars = new ItemStack[3][9];

    public BindHotbar() {
        super("BindHotbar", "Save and load hotbar layouts", Category.PLAYER);
    }

    @Override
    protected void onEnable() {
        if (mc.player == null) {
            disable();
            return;
        }

        int index = Integer.parseInt(slot.getValue()) - 1;

        // If slot is empty, save current hotbar
        if (savedHotbars[index][0] == null) {
            for (int i = 0; i < 9; i++) {
                savedHotbars[index][i] = mc.player.getInventory().getStack(i).copy();
            }
            mc.player.sendMessage(Text.literal("Hotbar saved to slot " + slot.getValue()), true);
        } else {
            mc.player.sendMessage(Text.literal("Hotbar loaded from slot " + slot.getValue()), true);
        }

        disable();
    }
}
