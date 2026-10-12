package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

public class Speed extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Speed mode", "Strafe", "Strafe", "Vanilla", "BHop"));
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Movement speed multiplier", 1.5, 0.5, 5.0, 0.1));

    public Speed() {
        super("Speed", "Move faster", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        boolean moving = mc.player.input.movementForward != 0 || mc.player.input.movementSideways != 0;
        if (!moving) return;

        switch (mode.getValue()) {
            case "Vanilla" -> {
                Vec3d vel = mc.player.getVelocity();
                mc.player.setVelocity(vel.x * speed.getValue(), vel.y, vel.z * speed.getValue());
            }
            case "Strafe" -> {
                double yaw = Math.toRadians(mc.player.getYaw());
                double forward = mc.player.input.movementForward;
                double strafe = mc.player.input.movementSideways;
                double spd = speed.getValue() * 0.2873;

                if (forward == 0 && strafe == 0) return;

                double angle = yaw;
                if (forward != 0) {
                    angle += (strafe > 0 ? -0.5 : strafe < 0 ? 0.5 : 0) * (forward > 0 ? Math.PI / 4 : -Math.PI / 4);
                    strafe = 0;
                    forward = forward > 0 ? 1 : -1;
                }

                mc.player.setVelocity(
                        -Math.sin(angle) * spd * forward + -Math.cos(angle) * spd * strafe,
                        mc.player.getVelocity().y,
                        Math.cos(angle) * spd * forward + -Math.sin(angle) * spd * strafe
                );
            }
            case "BHop" -> {
                if (mc.player.isOnGround()) {
                    mc.player.jump();
                }
                Vec3d vel = mc.player.getVelocity();
                mc.player.setVelocity(vel.x * speed.getValue(), vel.y, vel.z * speed.getValue());
            }
        }
    }
}
