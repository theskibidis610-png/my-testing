package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class QuickTurn extends Module {

    public QuickTurn() {
        super("QuickTurn", "Snap yaw to nearest 90 degree angle", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        boolean moving = mc.player.input.movementForward != 0 || mc.player.input.movementSideways != 0;
        if (moving) {
            float yaw = mc.player.getYaw();
            float snapped = Math.round(yaw / 90.0f) * 90.0f;
            mc.player.setYaw(snapped);
        }
    }
}
