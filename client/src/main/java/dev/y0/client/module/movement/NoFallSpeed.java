package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class NoFallSpeed extends Module {

    public NoFallSpeed() {
        super("NoFallSpeed", "No horizontal speed loss when falling", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.player.isOnGround()) return;
        double speed = Math.sqrt(mc.player.getVelocity().x * mc.player.getVelocity().x + mc.player.getVelocity().z * mc.player.getVelocity().z);
        if (speed < 0.2873) {
            double yaw = Math.toRadians(mc.player.getYaw());
            mc.player.setVelocity(
                    -Math.sin(yaw) * 0.2873,
                    mc.player.getVelocity().y,
                    Math.cos(yaw) * 0.2873
            );
        }
    }
}
