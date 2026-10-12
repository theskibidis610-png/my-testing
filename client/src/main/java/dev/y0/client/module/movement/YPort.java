package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

public class YPort extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "YPort speed multiplier", 1.0, 0.5, 3.0, 0.1));

    public YPort() {
        super("YPort", "Y-axis speed exploit for fast movement", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        boolean moving = mc.player.input.movementForward != 0 || mc.player.input.movementSideways != 0;
        if (!moving) return;

        if (mc.player.isOnGround()) {
            mc.player.jump();
        } else if (mc.player.getVelocity().y > 0) {
            Vec3d vel = mc.player.getVelocity();
            mc.player.setVelocity(vel.x * (1.0 + speed.getValue() * 0.1), -1.0, vel.z * (1.0 + speed.getValue() * 0.1));
        }
    }
}
