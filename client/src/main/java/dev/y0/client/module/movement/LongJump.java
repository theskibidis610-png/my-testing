package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class LongJump extends Module {
    private final NumberSetting boost = addSetting(new NumberSetting("Boost", "Jump boost", 1.5, 0.5, 5.0, 0.1));

    public LongJump() {
        super("LongJump", "Jump further", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (!mc.player.isOnGround() && mc.player.getVelocity().y > 0.2) {
            double yaw = Math.toRadians(mc.player.getYaw());
            mc.player.setVelocity(
                    -Math.sin(yaw) * boost.getValue() * 0.2,
                    mc.player.getVelocity().y,
                    Math.cos(yaw) * boost.getValue() * 0.2
            );
        }
    }
}
