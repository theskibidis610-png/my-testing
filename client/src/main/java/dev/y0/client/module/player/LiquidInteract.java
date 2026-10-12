package dev.y0.client.module.player;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class LiquidInteract extends Module {

    public LiquidInteract() {
        super("LiquidInteract", "Allows placing blocks on liquids", Category.PLAYER);
    }

    // Requires mixin on FluidBlock to return solid outline shape
    // when this module is enabled, allowing block placement on water/lava.
}
