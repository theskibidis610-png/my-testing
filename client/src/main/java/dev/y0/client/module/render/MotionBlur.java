package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class MotionBlur extends Module {
    private final NumberSetting amount = addSetting(new NumberSetting("Amount", "Blur intensity", 5.0, 1.0, 10.0, 1.0));

    public MotionBlur() {
        super("MotionBlur", "Apply motion blur post-processing effect", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Apply motion blur shader via post-processing pipeline
    }

    public double getAmount() {
        return amount.getValue();
    }
}
