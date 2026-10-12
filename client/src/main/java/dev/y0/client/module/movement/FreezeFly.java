package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class FreezeFly extends Module {

    public FreezeFly() {
        super("FreezeFly", "Freeze in place mid-air", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        mc.player.setVelocity(0, 0, 0);
        mc.player.setOnGround(false);
    }
}
