package dev.y0.client.module.movement;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class AntiSlip extends Module {

    public AntiSlip() {
        super("AntiSlip", "Remove slipperiness from blocks like ice", Category.MOVEMENT);
    }

    // Requires a mixin on Block.getSlipperiness to return 0.6f when enabled
}
