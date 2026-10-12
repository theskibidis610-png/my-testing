package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import dev.y0.client.setting.NumberSetting;

public class Zoom extends Module {
    private final NumberSetting zoomLevel = addSetting(new NumberSetting("Zoom Level", "FOV divisor when zoomed", 4.0, 1.0, 10.0, 0.5));
    private final BooleanSetting smooth = addSetting(new BooleanSetting("Smooth Zoom", "Animate zoom transition", true));

    private double originalFov = 70.0;
    private double currentZoomFov = 70.0;

    public Zoom() {
        super("Zoom", "Zoom in with a key", Category.RENDER);
    }

    @Override
    protected void onEnable() {
        if (mc.options != null) {
            originalFov = mc.options.getFov().getValue();
            currentZoomFov = originalFov;
        }
    }

    @Override
    protected void onDisable() {
        if (mc.options != null) {
            mc.options.getFov().setValue((int) originalFov);
        }
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.options == null) return;

        double targetFov = originalFov / zoomLevel.getValue();

        if (smooth.getValue()) {
            // Smoothly interpolate toward target FOV
            currentZoomFov += (targetFov - currentZoomFov) * 0.3;
            mc.options.getFov().setValue((int) Math.round(currentZoomFov));
        } else {
            mc.options.getFov().setValue((int) Math.round(targetFov));
        }
    }
}
