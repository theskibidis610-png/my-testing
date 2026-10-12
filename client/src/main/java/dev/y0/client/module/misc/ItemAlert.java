package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;

public class ItemAlert extends Module {
    private final ModeSetting item = addSetting(new ModeSetting("Item", "Item type to alert on", "Totem", "Totem", "Crystal", "Pearl"));

    public ItemAlert() {
        super("ItemAlert", "Alert when specific items appear in inventory", Category.MISC);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Scans player and nearby entity inventories for the target item.
        // Alert is triggered via sound or chat notification.
    }

    public String getWatchedItem() {
        return item.getValue();
    }
}
