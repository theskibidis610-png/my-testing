package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

public class EntitySpeed extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Entity speed multiplier", 2.0, 1.0, 5.0, 0.5));

    public EntitySpeed() {
        super("EntitySpeed", "Speed up rideable entities", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.player.getVehicle() == null) return;

        Entity vehicle = mc.player.getVehicle();
        float yaw = mc.player.getYaw();
        double forward = mc.player.input.movementForward;
        double strafe = mc.player.input.movementSideways;

        if (forward == 0 && strafe == 0) return;

        double rad = Math.toRadians(yaw);
        double spd = speed.getValue() * 0.3;
        double motionX = (-Math.sin(rad) * forward + Math.cos(rad) * strafe) * spd;
        double motionZ = (Math.cos(rad) * forward + Math.sin(rad) * strafe) * spd;

        vehicle.setVelocity(new Vec3d(motionX, vehicle.getVelocity().y, motionZ));
    }
}
