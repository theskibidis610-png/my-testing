package dev.y0.client.module.render;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class TunnelVision extends Module {
    private final NumberSetting intensity = addSetting(new NumberSetting("Intensity", "Vignette darkness intensity", 0.5, 0.0, 1.0, 0.05));

    public TunnelVision() {
        super("TunnelVision", "Darken edges of screen", Category.RENDER);
    }

    public double getIntensity() {
        return intensity.getValue();
    }
}
