package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

public class PotRefill extends Module {
    private final NumberSetting minSlots = addSetting(new NumberSetting("Min Slots", "Minimum potion slots in hotbar", 2.0, 1.0, 9.0, 1.0));

    public PotRefill() {
        super("PotRefill", "Auto refill potions", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        int potCount = 0;
        for (int i = 0; i < 9; i++) {
            if (mc.player.getInventory().getStack(i).getItem() == Items.SPLASH_POTION) potCount++;
        }
        if (potCount >= minSlots.getValue()) return;
        for (int i = 9; i < 36; i++) {
            if (mc.player.getInventory().getStack(i).getItem() == Items.SPLASH_POTION) {
                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, i, 0, SlotActionType.QUICK_MOVE, mc.player);
                return;
            }
        }
    }
}
