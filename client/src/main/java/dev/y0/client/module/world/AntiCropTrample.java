package dev.y0.client.module.world;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

/**
 * Prevents the player from trampling crops by landing on farmland.
 * Requires a mixin on {@code FarmlandBlock#onLandedUpon} to cancel
 * the event when this module is enabled.
 */
public class AntiCropTrample extends Module {

    public AntiCropTrample() {
        super("AntiCropTrample", "Prevent crop trampling", Category.WORLD);
    }

    // Mixin target: FarmlandBlock#onLandedUpon
    // Cancel the call when isEnabled() returns true.
}
