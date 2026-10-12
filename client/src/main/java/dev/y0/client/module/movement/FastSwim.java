package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

public class FastSwim extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Swim speed boost", 0.5, 0.1, 1.0, 0.05));

    public FastSwim() {
        super("FastSwim", "Swim faster", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (mc.player.isTouchingWater() || mc.player.isSubmergedInWater()) {
            Vec3d vel = mc.player.getVelocity();
            float yaw = mc.player.getYaw();
            double rad = Math.toRadians(yaw);
            double forward = mc.player.input.movementForward;
            double strafe = mc.player.input.movementSideways;

            double spd = speed.getValue();
            mc.player.setVelocity(
                    vel.x + (-Math.sin(rad) * forward + Math.cos(rad) * strafe) * spd * 0.1,
                    vel.y,
                    vel.z + (Math.cos(rad) * forward + Math.sin(rad) * strafe) * spd * 0.1
            );
        }
    }
}
