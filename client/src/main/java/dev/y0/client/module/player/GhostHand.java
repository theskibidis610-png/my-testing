package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

/**
 * GhostHand - Allows interacting with blocks through other blocks.
 * NOTE: Full functionality requires a mixin to modify
 * WorldRenderer or BlockView to ignore certain blocks during raycasting.
 * This module provides the toggle; the mixin should check if this module is enabled.
 */
public class GhostHand extends Module {

    public GhostHand() {
        super("GhostHand", "Interact with blocks through walls (requires mixin)", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        // Functionality handled by mixin that checks isEnabled()
        // The mixin should modify block raycasting to skip non-interactive blocks
    }
}
