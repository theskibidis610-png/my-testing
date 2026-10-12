package dev.y0.client.module.world;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class Timer extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Game speed multiplier", 2.0, 0.1, 10.0, 0.1));

    public Timer() {
        super("Timer", "Change game tick speed", Category.WORLD);
    }

    public float getTimerSpeed() {
        return (float) speed.getValue();
    }

    // Requires a mixin on RenderTickCounter to multiply tick delta
    // by getTimerSpeed() when this module is enabled.
}
