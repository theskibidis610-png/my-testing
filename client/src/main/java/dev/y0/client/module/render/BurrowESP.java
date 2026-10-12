package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class BurrowESP extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Detection range", 32.0, 8.0, 128.0, 4.0));

    public BurrowESP() {
        super("BurrowESP", "Show players burrowed inside blocks", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null) return;
        // Check for players inside solid blocks within range
    }

    public double getRange() {
        return range.getValue();
    }
}
