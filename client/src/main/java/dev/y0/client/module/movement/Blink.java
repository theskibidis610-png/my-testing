package dev.y0.client.module.movement;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class Blink extends Module {

    public Blink() {
        super("Blink", "Hold outgoing packets to teleport", Category.MOVEMENT);
    }

    // Requires a packet send mixin to queue all outgoing movement packets
    // while enabled, then flush them all on disable.
}
