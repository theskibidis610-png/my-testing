package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class VoidESP extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Scan range for void holes", 32.0, 8.0, 64.0, 4.0));

    public VoidESP() {
        super("VoidESP", "Highlight holes leading to the void", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null || mc.player == null) return;
        // Scan for blocks at minimum build height with air below
    }

    public double getRange() {
        return range.getValue();
    }
}
