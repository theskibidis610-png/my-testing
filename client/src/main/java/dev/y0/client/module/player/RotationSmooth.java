package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class RotationSmooth extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Rotation smoothing speed", 5.0, 1.0, 20.0, 0.5));
    private float targetYaw, targetPitch;

    public RotationSmooth() {
        super("RotationSmooth", "Smooths server-side head rotations", Category.PLAYER);
    }

    @Override
    protected void onEnable() {
        if (mc.player != null) {
            targetYaw = mc.player.getYaw();
            targetPitch = mc.player.getPitch();
        }
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        float factor = speed.getValue().floatValue() / 20.0f;
        targetYaw += (mc.player.getYaw() - targetYaw) * factor;
        targetPitch += (mc.player.getPitch() - targetPitch) * factor;
    }
}
