package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import dev.y0.client.setting.NumberSetting;

public class Animations extends Module {
    private final ModeSetting swingMode = addSetting(new ModeSetting("Swing Mode", "Animation style for hand swing", "Normal", "Normal", "1.7", "Smooth", "Spin"));
    private final NumberSetting swingSpeed = addSetting(new NumberSetting("Swing Speed", "Speed multiplier for swing animation", 1.0, 0.5, 3.0, 0.1));

    public Animations() {
        super("Animations", "Custom attack and use animations", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        // Modify hand swing progress based on speed setting
        if (mc.player.handSwinging) {
            int swingProgressInt = mc.player.handSwingTicks;
            int maxSwing = (int) (6 / swingSpeed.getValue());
            if (maxSwing < 1) maxSwing = 1;

            if (swingProgressInt >= maxSwing) {
                mc.player.handSwingTicks = 0;
                mc.player.handSwinging = false;
            }
        }

        // Full swing mode rendering (1.7, Smooth, Spin) requires a mixin on
        // HeldItemRenderer to transform the hand model based on swingMode.
        // The tick-based speed adjustment above works without a mixin.
    }
}
