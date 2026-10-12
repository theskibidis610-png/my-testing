package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class CustomFOV extends Module {
    private final NumberSetting fov = addSetting(new NumberSetting("FOV", "Field of view override", 90.0, 30.0, 160.0, 1.0));

    private double previousFov;

    public CustomFOV() {
        super("CustomFOV", "Override field of view", Category.RENDER);
    }

    @Override
    protected void onEnable() {
        if (mc.options != null) {
            previousFov = mc.options.getFov().getValue();
        }
    }

    @Override
    protected void onDisable() {
        if (mc.options != null) {
            mc.options.getFov().setValue((int) previousFov);
        }
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.options != null) {
            mc.options.getFov().setValue((int) fov.getValue());
        }
    }
}
