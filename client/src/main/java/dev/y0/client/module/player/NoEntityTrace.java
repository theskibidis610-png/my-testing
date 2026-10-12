package dev.y0.client.module.player;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class NoEntityTrace extends Module {

    public NoEntityTrace() {
        // Companion mixin skips entity ray-cast when holding blocks
        super("NoEntityTrace", "Ignores entities when placing blocks", Category.PLAYER);
    }

    // Requires mixin on GameRenderer.updateCrosshairTarget to skip entity
    // ray-cast when holding a block item and this module is enabled.
}
