package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class WallClimb extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Climb speed", 0.3, 0.1, 1.0, 0.05));

    public WallClimb() {
        super("WallClimb", "Climb any wall", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (mc.player.horizontalCollision) {
            mc.player.setVelocity(mc.player.getVelocity().x, speed.getValue(), mc.player.getVelocity().z);
            mc.player.setOnGround(true);
        }
    }
}
