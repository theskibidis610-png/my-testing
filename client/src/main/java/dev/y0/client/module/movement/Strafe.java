package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

public class Strafe extends Module {
    private final NumberSetting strength = addSetting(new NumberSetting("Strength", "Strafe strength", 1.0, 0.1, 2.0, 0.1));

    public Strafe() {
        super("Strafe", "Better air control", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.player.isOnGround()) return;
        Vec3d vel = mc.player.getVelocity();
        double speed = Math.sqrt(vel.x * vel.x + vel.z * vel.z);
        double yaw = Math.toRadians(mc.player.getYaw());
        double forward = mc.player.input.movementForward;
        double strafe = mc.player.input.movementSideways;
        if (forward == 0 && strafe == 0) return;
        mc.player.setVelocity(
                -Math.sin(yaw) * speed * strength.getValue(),
                vel.y,
                Math.cos(yaw) * speed * strength.getValue()
        );
    }
}
