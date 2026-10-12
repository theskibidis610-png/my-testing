package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class Radar extends Module {
    private final NumberSetting size = addSetting(new NumberSetting("Size", "Radar display size in pixels", 80.0, 40.0, 200.0, 10.0));
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Detection range in blocks", 50.0, 10.0, 200.0, 10.0));

    public Radar() {
        super("Radar", "Mini radar display on HUD", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null || mc.player == null) return;
        // Entity positions collected each tick for HUD overlay rendering
    }

    public double getSize() {
        return size.getValue();
    }

    public double getRange() {
        return range.getValue();
    }
}
