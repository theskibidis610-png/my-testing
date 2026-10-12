package dev.y0.client.module.movement;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class NarrowJump extends Module {

    public NarrowJump() {
        super("NarrowJump", "Jump through 1-block gaps", Category.MOVEMENT);
    }

    // Requires a mixin on Entity.getHeight or collision to shrink hitbox while jumping
}
