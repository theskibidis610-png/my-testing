package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;

public class RotationLock extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "What to lock", "Both", "Yaw", "Pitch", "Both"));
    private float savedYaw, savedPitch;

    public RotationLock() {
        super("RotationLock", "Lock movement rotation angles", Category.MOVEMENT);
    }

    @Override
    protected void onEnable() {
        if (mc.player != null) {
            savedYaw = mc.player.getYaw();
            savedPitch = mc.player.getPitch();
        }
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        switch (mode.getValue()) {
            case "Yaw" -> mc.player.setYaw(savedYaw);
            case "Pitch" -> mc.player.setPitch(savedPitch);
            case "Both" -> {
                mc.player.setYaw(savedYaw);
                mc.player.setPitch(savedPitch);
            }
        }
    }
}
