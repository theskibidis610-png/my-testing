package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class WebMove extends Module {

    public WebMove() {
        super("WebMove", "Move through webs normally", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Actual web slowdown prevention requires a mixin to CobwebBlock or Entity#slowMovement
        // This tick handler ensures the module state is tracked for the mixin to check
    }

    public boolean shouldCancelWebSlow() {
        return isEnabled();
    }
}
