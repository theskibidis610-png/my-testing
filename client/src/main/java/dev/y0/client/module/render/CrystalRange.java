package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class CrystalRange extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Crystal damage range to display", 12.0, 4.0, 24.0, 1.0));

    public CrystalRange() {
        super("CrystalRange", "Show end crystal damage range", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null) return;
        // Render sphere around end crystals showing damage range
    }

    public double getRange() {
        return range.getValue();
    }
}
