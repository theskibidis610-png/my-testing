package dev.y0.client.module.combat;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class Reach extends Module {
    private final NumberSetting reach = addSetting(new NumberSetting("Range", "Attack reach distance", 3.5, 3.0, 6.0, 0.1));

    public Reach() {
        super("Reach", "Extend attack reach", Category.COMBAT);
    }

    public double getReachDistance() {
        return reach.getValue();
    }

    // Requires a mixin on ClientPlayerInteractionManager.getReachDistance()
    // to return this value when enabled.
}
