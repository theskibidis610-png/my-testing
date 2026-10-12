package dev.y0.client.module.player;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class AntiCactus extends Module {

    public AntiCactus() {
        super("AntiCactus", "Prevents cactus damage", Category.PLAYER);
    }

    // Requires mixin on CactusBlock to cancel collision damage
    // when this module is enabled. Check isEnabled() from mixin.
}
