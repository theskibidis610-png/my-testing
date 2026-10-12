package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class ScreenShake extends Module {
    private final NumberSetting intensity = addSetting(new NumberSetting("Intensity", "Screen shake intensity multiplier", 1.0, 0.0, 5.0, 0.1));

    public ScreenShake() {
        super("ScreenShake", "Customize screen shake effects", Category.MISC);
    }

    @EventHandler
    public void onTick(EventTick event) {
        // Shake intensity is applied via mixin on GameRenderer.bobView()
        // or by modifying the camera during the render event.
    }

    public double getIntensity() {
        return intensity.getValue();
    }
}
