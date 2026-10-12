package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class PortalFly extends Module {

    public PortalFly() {
        super("PortalFly", "Fly inside nether portal frames", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.hasPortalCooldown()) return;

        mc.player.getAbilities().flying = true;
        mc.player.getAbilities().setFlySpeed(0.05f);
    }

    @Override
    protected void onDisable() {
        if (mc.player != null && !mc.player.isCreative()) {
            mc.player.getAbilities().flying = false;
        }
    }
}
