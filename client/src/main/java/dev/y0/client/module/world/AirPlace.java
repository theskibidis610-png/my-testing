package dev.y0.client.module.world;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

/**
 * Allows placing blocks in air without needing an adjacent block.
 * Requires a mixin into {@code ItemUsageContext} or {@code BlockItem#canPlace}
 * to bypass the placement validation check.
 */
public class AirPlace extends Module {
    public AirPlace() {
        super("AirPlace", "Place blocks in air without adjacent blocks", Category.WORLD);
    }

    // Functionality is handled by mixin:
    // Mixin into BlockItem#canPlace or ClientPlayerInteractionManager
    // to allow placement when this module is enabled.
    // Check: Y0Client.getInstance().getModuleManager().isEnabled(AirPlace.class)
}
