package dev.y0.client.module.movement;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class SafeWalk extends Module {

    public SafeWalk() {
        super("SafeWalk", "Prevent walking off edges", Category.MOVEMENT);
    }

    // Requires a mixin on ClientPlayerEntity.clipAtLedge to return true
}
