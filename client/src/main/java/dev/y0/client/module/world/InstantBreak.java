package dev.y0.client.module.world;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

/**
 * Breaks blocks instantly regardless of hardness.
 * Requires a mixin into {@code ClientPlayerInteractionManager} to set
 * the block breaking progress to 1.0 immediately, or to send
 * STOP_DESTROY_BLOCK right after START_DESTROY_BLOCK.
 *
 * Mixin target: ClientPlayerInteractionManager#attackBlock
 * Check: Y0Client.getInstance().getModuleManager().isEnabled(InstantBreak.class)
 */
public class InstantBreak extends Module {
    public InstantBreak() {
        super("InstantBreak", "Break blocks instantly regardless of hardness", Category.WORLD);
    }

    // All logic is handled by mixin into ClientPlayerInteractionManager.
    // The mixin should intercept updateBlockBreakingProgress and set
    // currentBreakingProgress = 1.0f when this module is enabled.
}
