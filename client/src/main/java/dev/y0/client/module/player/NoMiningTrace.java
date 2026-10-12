package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class NoMiningTrace extends Module {

    public NoMiningTrace() {
        super("NoMiningTrace", "Prevents mining through entities", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.interactionManager == null) return;

        // Cancel block breaking if crosshair is targeting an entity
        if (mc.crosshairTarget != null &&
                mc.crosshairTarget.getType() == net.minecraft.util.hit.HitResult.Type.ENTITY) {
            mc.interactionManager.cancelBlockBreaking();
        }
    }
}
