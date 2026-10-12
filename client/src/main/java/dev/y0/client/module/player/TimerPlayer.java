package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class TimerPlayer extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Timer speed multiplier", 1.0, 0.1, 5.0, 0.1));

    public TimerPlayer() {
        super("TimerPlayer", "Changes client-side tick speed for the player", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        // Adjusts the render tick delta via mixin on MinecraftClient
        // to speed up or slow down player-specific actions.
    }

    public float getSpeed() {
        return speed.getValue().floatValue();
    }
}
