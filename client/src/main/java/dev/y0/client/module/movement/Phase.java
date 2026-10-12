package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;

public class Phase extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Phase method", "Vanilla", "Vanilla", "NCP"));

    public Phase() {
        super("Phase", "Walk through blocks", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        mc.player.noClip = true;
        mc.player.setOnGround(false);
        mc.player.getAbilities().flying = false;
    }

    @Override
    protected void onDisable() {
        if (mc.player != null) mc.player.noClip = false;
    }
}
