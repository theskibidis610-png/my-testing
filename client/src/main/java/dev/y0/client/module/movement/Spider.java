package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class Spider extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Climb speed", 0.2, 0.05, 0.5, 0.05));

    public Spider() {
        super("Spider", "Climb walls like a spider", Category.MOVEMENT);
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
