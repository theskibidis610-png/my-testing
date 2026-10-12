package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import dev.y0.client.setting.NumberSetting;

public class ElytraFly extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Elytra fly method", "Boost", "Boost", "Control", "Firework"));
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Flight speed", 1.5, 0.5, 5.0, 0.1));

    public ElytraFly() {
        super("ElytraFly", "Enhanced elytra flight", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || !mc.player.isFallFlying()) return;

        switch (mode.getValue()) {
            case "Control" -> {
                double yaw = Math.toRadians(mc.player.getYaw());
                double pitch = Math.toRadians(mc.player.getPitch());
                double spd = speed.getValue() * 0.05;
                mc.player.setVelocity(
                        -Math.sin(yaw) * Math.cos(pitch) * spd,
                        -Math.sin(pitch) * spd,
                        Math.cos(yaw) * Math.cos(pitch) * spd
                );
            }
            case "Boost" -> {
                double yaw = Math.toRadians(mc.player.getYaw());
                mc.player.addVelocity(-Math.sin(yaw) * 0.05 * speed.getValue(), 0, Math.cos(yaw) * 0.05 * speed.getValue());
            }
        }
    }
}
