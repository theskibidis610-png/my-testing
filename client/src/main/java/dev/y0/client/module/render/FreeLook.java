package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class FreeLook extends Module {
    private final BooleanSetting invert = addSetting(new BooleanSetting("Invert", "Invert free look controls", false));

    private float cameraYaw;
    private float cameraPitch;
    private float playerYaw;
    private float playerPitch;

    public FreeLook() {
        super("FreeLook", "Look around without changing player direction", Category.RENDER);
    }

    @Override
    protected void onEnable() {
        if (mc.player != null) {
            cameraYaw = mc.player.getYaw();
            cameraPitch = mc.player.getPitch();
            playerYaw = mc.player.getYaw();
            playerPitch = mc.player.getPitch();
        }
    }

    @Override
    protected void onDisable() {
        if (mc.player != null) {
            mc.player.setYaw(playerYaw);
            mc.player.setPitch(playerPitch);
        }
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        // Store the current camera angles and restore the player's
        // locked direction each tick so movement stays consistent
        cameraYaw = mc.player.getYaw();
        cameraPitch = mc.player.getPitch();

        // Requires a mixin on Camera.update to use cameraYaw/cameraPitch
        // for rendering while keeping the player facing playerYaw/playerPitch.
        // The invert setting should negate mouse delta in that mixin.
    }

    public float getCameraYaw() { return cameraYaw; }
    public float getCameraPitch() { return cameraPitch; }
    public float getPlayerYaw() { return playerYaw; }
    public float getPlayerPitch() { return playerPitch; }
    public boolean isInverted() { return invert.getValue(); }
}
