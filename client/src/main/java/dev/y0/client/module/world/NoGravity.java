package dev.y0.client.module.world;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

/**
 * Prevents gravity-affected blocks (sand, gravel, concrete powder) from falling.
 * Requires a mixin into {@code FallingBlock#onBlockAdded} or
 * {@code FallingBlockEntity#tick} to cancel the falling behavior
 * when this module is enabled.
 *
 * Mixin target: FallingBlock
 * Check: Y0Client.getInstance().getModuleManager().isEnabled(NoGravity.class)
 */
public class NoGravity extends Module {
    public NoGravity() {
        super("NoGravity", "Disable gravity for sand, gravel and other falling blocks", Category.WORLD);
    }

    // All logic is handled via mixin into FallingBlock.
    // The mixin should cancel the scheduledTick / onBlockAdded methods
    // to prevent FallingBlockEntity from spawning.
}
