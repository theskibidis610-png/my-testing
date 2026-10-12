package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;

public class ItemModel extends Module {
    private final ModeSetting style = addSetting(new ModeSetting("Style", "Item render style", "Normal", "Normal", "Flat", "Old"));

    public ItemModel() {
        super("ItemModel", "Custom held item model style", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Item model transform applied via held item render mixin
    }

    public String getStyle() {
        return style.getValue();
    }
}
