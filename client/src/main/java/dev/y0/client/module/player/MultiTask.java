package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

/**
 * MultiTask - Allows using items while mining or eating simultaneously.
 * NOTE: Full functionality requires a mixin to MinecraftClient#doItemUse
 * and MinecraftClient#handleBlockBreaking to remove the mutual exclusion
 * checks. This module provides the toggle; the mixin should check if this
 * module is enabled.
 */
public class MultiTask extends Module {

    public MultiTask() {
        super("MultiTask", "Use items while mining or eating (requires mixin)", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        // Functionality handled by mixin that checks isEnabled()
        // The mixin removes the isUsingItem() check from handleBlockBreaking
        // and the isBreakingBlock check from doItemUse
    }
}
