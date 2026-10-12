package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class Glide extends Module {
    private final NumberSetting fallSpeed = addSetting(new NumberSetting("Fall Speed", "How slowly you fall", 0.1, 0.01, 0.5, 0.01));

    public Glide() {
        super("Glide", "Glide slowly when falling", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (!mc.player.isOnGround() && mc.player.getVelocity().y < 0) {
            mc.player.setVelocity(
                    mc.player.getVelocity().x,
                    -fallSpeed.getValue(),
                    mc.player.getVelocity().z
            );
        }
    }
}
