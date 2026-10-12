package dev.y0.client.module.player;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class KeepSprint extends Module {

    public KeepSprint() {
        super("KeepSprint", "Prevents sprint from resetting when hitting entities", Category.PLAYER);
    }

    // Requires mixin on ClientPlayerEntity.attack to prevent
    // setSprinting(false) when this module is enabled.
}
