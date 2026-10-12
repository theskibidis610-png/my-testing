package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class HighJump extends Module {
    private final NumberSetting multiplier = addSetting(new NumberSetting("Height", "Jump height multiplier", 2.0, 1.1, 5.0, 0.1));

    public HighJump() {
        super("HighJump", "Jump higher than normal", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (!mc.player.isOnGround() && mc.player.getVelocity().y > 0 && mc.player.getVelocity().y < 0.42) {
            mc.player.setVelocity(
                    mc.player.getVelocity().x,
                    mc.player.getVelocity().y * multiplier.getValue(),
                    mc.player.getVelocity().z
            );
        }
    }
}
