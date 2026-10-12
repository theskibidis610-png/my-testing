package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class HealthBar extends Module {
    private final NumberSetting scale = addSetting(new NumberSetting("Scale", "Health bar size scale", 1.0, 0.5, 3.0, 0.1));

    public HealthBar() {
        super("HealthBar", "Show health bars above entities", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null) return;
        // Render health bars above living entities
    }

    public double getScale() {
        return scale.getValue();
    }
}
