package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class Jetpack extends Module {
    private final NumberSetting boost = addSetting(new NumberSetting("Boost", "Upward boost strength", 0.5, 0.1, 1.0, 0.05));

    public Jetpack() {
        super("Jetpack", "Fly like a jetpack", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (mc.options.jumpKey.isPressed()) {
            mc.player.setVelocity(mc.player.getVelocity().add(0, boost.getValue(), 0));
        }
    }
}
