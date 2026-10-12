package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class NoRotation extends Module {
    private float lockedYaw, lockedPitch;

    public NoRotation() {
        super("NoRotation", "Lock rotation so movement direction stays fixed", Category.MOVEMENT);
    }

    @Override
    protected void onEnable() {
        if (mc.player != null) {
            lockedYaw = mc.player.getYaw();
            lockedPitch = mc.player.getPitch();
        }
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        mc.player.setYaw(lockedYaw);
        mc.player.setPitch(lockedPitch);
    }
}
