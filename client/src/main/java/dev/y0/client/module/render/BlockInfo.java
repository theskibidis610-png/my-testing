package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class BlockInfo extends Module {
    private final BooleanSetting coords = addSetting(new BooleanSetting("Coords", "Show block coordinates", true));

    public BlockInfo() {
        super("BlockInfo", "Show info about the targeted block", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.crosshairTarget == null) return;
        // Display block name and optionally coordinates on HUD
    }

    public boolean showCoords() {
        return coords.getValue();
    }
}
