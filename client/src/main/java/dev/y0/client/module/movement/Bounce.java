package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class Bounce extends Module {
    private final NumberSetting multiplier = addSetting(new NumberSetting("Multiplier", "Bounce strength", 1.0, 0.5, 3.0, 0.1));
    private boolean wasInAir;

    public Bounce() {
        super("Bounce", "Bounce on landing", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (mc.player.isOnGround() && wasInAir) {
            mc.player.jump();
            mc.player.setVelocity(mc.player.getVelocity().x, mc.player.getVelocity().y * multiplier.getValue(), mc.player.getVelocity().z);
        }
        wasInAir = !mc.player.isOnGround();
    }
}
