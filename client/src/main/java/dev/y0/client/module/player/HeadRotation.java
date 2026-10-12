package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class HeadRotation extends Module {
    private final BooleanSetting lockPitch = addSetting(new BooleanSetting("Lock Pitch", "Lock vertical head rotation", true));
    private final BooleanSetting lockYaw = addSetting(new BooleanSetting("Lock Yaw", "Lock horizontal head rotation", false));

    private float savedPitch;
    private float savedYaw;

    public HeadRotation() {
        super("HeadRotation", "Locks head rotation to current angles", Category.PLAYER);
    }

    @Override
    protected void onEnable() {
        if (mc.player != null) {
            savedPitch = mc.player.getPitch();
            savedYaw = mc.player.getYaw();
        }
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (lockPitch.getValue()) {
            mc.player.setPitch(savedPitch);
        }
        if (lockYaw.getValue()) {
            mc.player.setYaw(savedYaw);
        }
    }
}
