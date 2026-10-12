package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;

public class AutoGap extends Module {
    private final NumberSetting healthThreshold = addSetting(new NumberSetting("Health", "Eat golden apple below this health", 10.0, 1.0, 20.0, 1.0));

    private int previousSlot = -1;

    public AutoGap() {
        super("AutoGap", "Automatically eats golden apples when low on health", Category.PLAYER);
    }

    @Override
    protected void onDisable() {
        if (mc.player != null) {
            mc.options.useKey.setPressed(false);
            if (previousSlot != -1) {
                mc.player.getInventory().selectedSlot = previousSlot;
                previousSlot = -1;
            }
        }
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (mc.player.getHealth() >= healthThreshold.getValue()) {
            if (previousSlot != -1) {
                mc.options.useKey.setPressed(false);
                mc.player.getInventory().selectedSlot = previousSlot;
                previousSlot = -1;
            }
            return;
        }

        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.player.getInventory().getStack(i);
            if (stack.getItem() == Items.GOLDEN_APPLE || stack.getItem() == Items.ENCHANTED_GOLDEN_APPLE) {
                if (previousSlot == -1) previousSlot = mc.player.getInventory().selectedSlot;
                mc.player.getInventory().selectedSlot = i;
                mc.options.useKey.setPressed(true);
                return;
            }
        }
    }
}
