package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class AirStrafe extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Air strafe speed", 0.3, 0.1, 1.0, 0.05));

    public AirStrafe() {
        super("AirStrafe", "Strafe while in the air", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.player.isOnGround()) return;

        double yaw = Math.toRadians(mc.player.getYaw());
        double forward = mc.player.input.movementForward;
        double strafe = mc.player.input.movementSideways;
        if (forward == 0 && strafe == 0) return;

        mc.player.setVelocity(
                -Math.sin(yaw) * speed.getValue(),
                mc.player.getVelocity().y,
                Math.cos(yaw) * speed.getValue()
        );
    }
}
