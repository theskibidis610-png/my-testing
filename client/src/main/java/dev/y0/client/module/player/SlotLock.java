package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class SlotLock extends Module {
    private final BooleanSetting hotbar = addSetting(new BooleanSetting("Hotbar", "Lock hotbar slots from being moved", true));

    public SlotLock() {
        super("SlotLock", "Prevents accidental movement of inventory slots", Category.PLAYER);
    }

    public boolean isSlotLocked(int slot) {
        if (!isEnabled()) return false;
        if (hotbar.getValue() && slot >= 0 && slot <= 8) return true;
        return false;
    }

    @EventHandler
    public void onTick(EventTick event) {
        // Slot locking is checked via isSlotLocked from mixin
    }
}
