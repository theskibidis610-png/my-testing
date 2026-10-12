package dev.y0.client.module.movement;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class SnowShoe extends Module {

    public SnowShoe() {
        super("SnowShoe", "Walk on top of snow layers without sinking", Category.MOVEMENT);
    }

    // Requires a mixin on snow layer collision to treat layers as full blocks
}
