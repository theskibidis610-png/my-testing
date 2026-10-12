package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

/**
 * NoRotate - Prevents the server from forcefully rotating the player.
 * NOTE: Full functionality requires a mixin to ClientPlayNetworkHandler
 * to cancel or ignore PlayerPositionLookS2CPacket rotation values.
 * This module provides the toggle; the mixin should check if this module
 * is enabled and only apply the position, not the rotation.
 */
public class NoRotate extends Module {

    public NoRotate() {
        super("NoRotate", "Prevents server rotation packets (requires mixin)", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        // Functionality handled by mixin that checks isEnabled()
        // The mixin should intercept PlayerPositionLookS2CPacket
        // and ignore the yaw/pitch fields while keeping position
    }
}
