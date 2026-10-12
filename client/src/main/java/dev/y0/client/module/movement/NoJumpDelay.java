package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class NoJumpDelay extends Module {

    public NoJumpDelay() {
        super("NoJumpDelay", "Remove jump cooldown", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        mc.player.jumpingCooldown = 0;
    }
}
