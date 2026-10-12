package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.util.math.Vec3d;

public class Freecam extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Fly speed", 1.0, 0.1, 10.0, 0.1));

    private double savedX, savedY, savedZ;
    private float savedYaw, savedPitch;

    public Freecam() {
        super("Freecam", "Fly around without actually moving", Category.PLAYER);
    }

    @Override
    protected void onEnable() {
        if (mc.player == null) return;

        savedX = mc.player.getX();
        savedY = mc.player.getY();
        savedZ = mc.player.getZ();
        savedYaw = mc.player.getYaw();
        savedPitch = mc.player.getPitch();
    }

    @Override
    protected void onDisable() {
        if (mc.player == null) return;

        mc.player.setPosition(savedX, savedY, savedZ);
        mc.player.setYaw(savedYaw);
        mc.player.setPitch(savedPitch);
        mc.player.setVelocity(Vec3d.ZERO);
        mc.player.noClip = false;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        mc.player.setVelocity(Vec3d.ZERO);
        mc.player.noClip = true;
        mc.player.setOnGround(false);

        double flySpeed = speed.getValue() * 0.5;
        Vec3d forward = Vec3d.fromPolar(0, mc.player.getYaw()).normalize();
        Vec3d right = Vec3d.fromPolar(0, mc.player.getYaw() + 90).normalize();
        Vec3d movement = Vec3d.ZERO;

        if (mc.options.forwardKey.isPressed()) {
            movement = movement.add(forward.multiply(flySpeed));
        }
        if (mc.options.backKey.isPressed()) {
            movement = movement.add(forward.multiply(-flySpeed));
        }
        if (mc.options.leftKey.isPressed()) {
            movement = movement.add(right.multiply(-flySpeed));
        }
        if (mc.options.rightKey.isPressed()) {
            movement = movement.add(right.multiply(flySpeed));
        }
        if (mc.options.jumpKey.isPressed()) {
            movement = movement.add(0, flySpeed, 0);
        }
        if (mc.options.sneakKey.isPressed()) {
            movement = movement.add(0, -flySpeed, 0);
        }

        mc.player.setPosition(mc.player.getX() + movement.x, mc.player.getY() + movement.y, mc.player.getZ() + movement.z);

        // Note: For proper freecam, a mixin is needed on:
        // - sendMovementPackets to prevent sending position updates to server
        // - PlayerEntity.pushOutOfBlocks to prevent being pushed out
        // - Entity.isInvisibleTo to hide the fake player
    }
}
