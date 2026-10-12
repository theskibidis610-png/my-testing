package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class FastFall extends Module {
    private final NumberSetting fallSpeed = addSetting(new NumberSetting("Fall Speed", "How fast you fall", 1.0, 0.5, 5.0, 0.1));

    public FastFall() {
        super("FastFall", "Fall faster than normal", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (!mc.player.isOnGround() && mc.player.getVelocity().y < 0) {
            mc.player.setVelocity(
                    mc.player.getVelocity().x,
                    mc.player.getVelocity().y * fallSpeed.getValue(),
                    mc.player.getVelocity().z
            );
        }
    }
}
