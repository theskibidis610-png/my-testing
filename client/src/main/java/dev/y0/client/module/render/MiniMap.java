package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class MiniMap extends Module {
    private final NumberSetting size = addSetting(new NumberSetting("Size", "Minimap size in pixels", 100.0, 50.0, 200.0, 10.0));
    private final NumberSetting zoom = addSetting(new NumberSetting("Zoom", "Zoom level", 1.0, 0.5, 4.0, 0.5));

    public MiniMap() {
        super("MiniMap", "Simple minimap on HUD", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null || mc.player == null) return;
        // Render top-down minimap in corner of screen
    }

    public int getSize() { return (int) size.getValue(); }
    public double getZoom() { return zoom.getValue(); }
}
