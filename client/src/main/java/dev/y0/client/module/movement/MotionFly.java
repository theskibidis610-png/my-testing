package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

public class MotionFly extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Flight speed", 1.0, 0.1, 5.0, 0.1));

    public MotionFly() {
        super("MotionFly", "Motion-based flight using velocity", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        Vec3d look = mc.player.getRotationVector();
        double forward = mc.player.input.movementForward;
        double vertical = 0;

        if (mc.options.jumpKey.isPressed()) vertical = speed.getValue() * 0.5;
        if (mc.options.sneakKey.isPressed()) vertical = -speed.getValue() * 0.5;

        mc.player.setVelocity(
                look.x * forward * speed.getValue() * 0.5,
                vertical,
                look.z * forward * speed.getValue() * 0.5
        );
    }

    @Override
    protected void onDisable() {
        if (mc.player != null) {
            mc.player.setVelocity(0, 0, 0);
        }
    }
}
