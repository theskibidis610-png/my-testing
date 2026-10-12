package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.Vec3d;

public class BoatFly extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Flight speed in boat", 2.0, 0.5, 5.0, 0.5));

    public BoatFly() {
        super("BoatFly", "Fly while riding a boat", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || !(mc.player.getVehicle() instanceof BoatEntity boat)) return;

        boat.setVelocity(Vec3d.ZERO);
        double spd = speed.getValue() * 0.5;
        float yaw = mc.player.getYaw();

        double motionX = -Math.sin(Math.toRadians(yaw)) * spd * mc.player.input.movementForward;
        double motionZ = Math.cos(Math.toRadians(yaw)) * spd * mc.player.input.movementForward;
        double motionY = mc.options.jumpKey.isPressed() ? spd : mc.options.sneakKey.isPressed() ? -spd : 0;

        boat.setVelocity(motionX, motionY, motionZ);
    }
}
