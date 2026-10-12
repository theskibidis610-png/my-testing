package dev.y0.client.module.player;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class AntiEntityPush extends Module {

    public AntiEntityPush() {
        // Companion mixin cancels Entity.pushAwayFrom for local player
        super("AntiEntityPush", "Prevents entities from pushing you", Category.PLAYER);
    }

    // Requires mixin on Entity.pushAwayFrom to cancel
    // when the target entity is the local player and this module is enabled.
}
