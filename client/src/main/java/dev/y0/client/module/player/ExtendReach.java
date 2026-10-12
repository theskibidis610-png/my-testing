package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

/**
 * ExtendReach - Extends the player's block interaction reach distance.
 * NOTE: Full functionality may require a mixin to override
 * ClientPlayerInteractionManager#getReachDistance(). This module exposes
 * the setting; other modules or mixins should reference getReachDistance().
 */
public class ExtendReach extends Module {
    private final NumberSetting distance = addSetting(new NumberSetting("Distance", "Block reach distance", 5.0, 4.5, 8.0, 0.1));

    public ExtendReach() {
        super("ExtendReach", "Extend block interaction reach distance", Category.PLAYER);
    }

    public double getReachDistance() {
        return distance.getValue();
    }

    @EventHandler
    public void onTick(EventTick event) {
        // Reach distance is applied via getReachDistance() called by mixin
        // or by other modules that query this value
    }
}
