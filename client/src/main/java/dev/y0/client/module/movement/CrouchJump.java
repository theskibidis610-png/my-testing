package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class CrouchJump extends Module {

    public CrouchJump() {
        super("CrouchJump", "Allow jumping while crouching", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.isSneaking() && mc.options.jumpKey.isPressed() && mc.player.isOnGround()) {
            mc.player.jump();
        }
    }
}
