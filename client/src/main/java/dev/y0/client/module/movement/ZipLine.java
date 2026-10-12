package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class ZipLine extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Zip speed along blocks", 1.5, 0.5, 5.0, 0.5));

    public ZipLine() {
        super("ZipLine", "Zip along blocks at high speed", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.horizontalCollision && !mc.player.isOnGround()) {
            double yaw = Math.toRadians(mc.player.getYaw());
            mc.player.setVelocity(
                    -Math.sin(yaw) * speed.getValue() * 0.2,
                    0.0,
                    Math.cos(yaw) * speed.getValue() * 0.2
            );
        }
    }
}
