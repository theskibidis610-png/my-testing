package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class MouseSens extends Module {
    private final NumberSetting sensitivity = addSetting(new NumberSetting("Sensitivity", "Mouse sensitivity multiplier", 1.0, 0.1, 5.0, 0.1));

    private double originalSensitivity;

    public MouseSens() {
        super("MouseSens", "Override mouse sensitivity", Category.MISC);
    }

    @Override
    protected void onEnable() {
        originalSensitivity = mc.options.getMouseSensitivity().getValue();
    }

    @Override
    protected void onDisable() {
        mc.options.getMouseSensitivity().setValue(originalSensitivity);
    }

    @EventHandler
    public void onTick(EventTick event) {
        mc.options.getMouseSensitivity().setValue(sensitivity.getValue());
    }
}
