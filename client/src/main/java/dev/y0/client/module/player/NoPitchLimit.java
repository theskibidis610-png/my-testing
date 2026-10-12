package dev.y0.client.module.player;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class NoPitchLimit extends Module {

    public NoPitchLimit() {
        // Companion mixin removes pitch clamp in Entity.changeLookDirection
        super("NoPitchLimit", "Removes the 90 degree pitch limit", Category.PLAYER);
    }

    // Requires mixin on Entity.changeLookDirection to remove
    // the pitch clamp of -90 to 90 when this module is enabled.
}
