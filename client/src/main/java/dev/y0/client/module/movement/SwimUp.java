package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class SwimUp extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Upward swim speed", 0.5, 0.1, 2.0, 0.1));

    public SwimUp() {
        super("SwimUp", "Swim upward faster", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.isTouchingWater() && mc.options.jumpKey.isPressed()) {
            mc.player.setVelocity(
                    mc.player.getVelocity().x,
                    speed.getValue(),
                    mc.player.getVelocity().z
            );
        }
    }
}
