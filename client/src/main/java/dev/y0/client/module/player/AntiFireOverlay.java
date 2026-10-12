package dev.y0.client.module.player;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class AntiFireOverlay extends Module {

    public AntiFireOverlay() {
        super("AntiFireOverlay", "Removes the fire overlay effect from the screen", Category.PLAYER);
    }

    // Requires mixin on InGameOverlayRenderer to skip fire rendering
    // when this module is enabled.
}
