package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;

public class SwingAnimation extends Module {
    private final ModeSetting hand = addSetting(new ModeSetting("Hand", "Which hand to animate", "MainHand", "MainHand", "OffHand", "Both"));

    public SwingAnimation() {
        super("SwingAnimation", "Custom hand swing animation", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Swing animation modified via hand render mixin
    }

    public String getHand() {
        return hand.getValue();
    }
}
