package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class PortalGodMode extends Module {

    public PortalGodMode() {
        super("PortalGodMode", "Exploit god mode while in portals", Category.MISC);
    }

    // NOTE: This exploit works by preventing the client from sending
    // the TeleportConfirm packet while inside a nether portal,
    // causing the server to not process damage. Requires a mixin
    // on ClientPlayNetworkHandler to suppress PlayerMoveC2SPacket
    // when the player is inside a portal block.

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.getNetworkHandler() == null) return;

        // Placeholder: portal god mode logic is primarily handled by
        // a mixin that suppresses position confirmation packets
        // while the player's netherPortalTime > 0.
    }
}
