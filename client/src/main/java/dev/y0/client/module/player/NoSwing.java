package dev.y0.client.module.player;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class NoSwing extends Module {

    public NoSwing() {
        super("NoSwing", "Hides hand swing animation", Category.PLAYER);
    }

    // Requires mixin on ClientPlayerEntity.swingHand to cancel
    // the swing animation when this module is enabled.
}
