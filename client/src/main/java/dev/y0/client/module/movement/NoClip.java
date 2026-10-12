package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class NoClip extends Module {

    public NoClip() {
        super("NoClip", "Walk through blocks", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        mc.player.noClip = true;
        mc.player.setOnGround(false);

        double speed = 0.3;
        mc.player.getAbilities().flying = true;
        mc.player.getAbilities().setFlySpeed((float) speed * 0.05f);
    }

    @Override
    protected void onDisable() {
        if (mc.player != null) {
            mc.player.noClip = false;
            if (!mc.player.isCreative()) {
                mc.player.getAbilities().flying = false;
            }
        }
    }
}
