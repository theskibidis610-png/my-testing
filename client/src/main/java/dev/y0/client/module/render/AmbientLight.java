package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class AmbientLight extends Module {
    private final NumberSetting brightness = addSetting(new NumberSetting("Brightness", "Ambient light level", 10.0, 0.0, 15.0, 1.0));

    public AmbientLight() {
        super("AmbientLight", "Adjust ambient light level", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null) return;
        // Ambient light adjustment applied via mixin on getLightLevel
    }

    public double getBrightness() {
        return brightness.getValue();
    }
}
