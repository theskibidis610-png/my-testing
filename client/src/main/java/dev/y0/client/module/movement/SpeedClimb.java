package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class SpeedClimb extends Module {
    private final NumberSetting multiplier = addSetting(new NumberSetting("Multiplier", "Climb speed multiplier", 2.0, 1.0, 5.0, 0.5));

    public SpeedClimb() {
        super("SpeedClimb", "Speed up all climbing surfaces", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.isClimbing()) {
            mc.player.setVelocity(
                    mc.player.getVelocity().x,
                    mc.player.getVelocity().y * multiplier.getValue(),
                    mc.player.getVelocity().z
            );
        }
    }
}
