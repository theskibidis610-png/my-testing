package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class Compass extends Module {
    private final BooleanSetting coordinates = addSetting(new BooleanSetting("Coordinates", "Show XYZ coordinates", true));

    public Compass() {
        super("Compass", "HUD compass with direction", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Render compass bar on HUD based on player yaw
    }

    public boolean showCoordinates() {
        return coordinates.getValue();
    }
}
