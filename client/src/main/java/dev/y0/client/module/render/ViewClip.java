package dev.y0.client.module.render;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class ViewClip extends Module {
    private final NumberSetting distance = addSetting(new NumberSetting("Distance", "Third person camera distance", 4.0, 1.0, 20.0, 0.5));

    public ViewClip() {
        super("ViewClip", "Change third person camera distance", Category.RENDER);
    }

    // Requires a mixin on Camera.clipToSpace or Camera.update to override
    // the default 4.0 block camera distance with the configured value.
    // The mixin should return this module's distance instead of the
    // vanilla calculation when the module is enabled.

    public double getDistance() {
        return distance.getValue();
    }
}
