package dev.y0.client.module.combat;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class Hitbox extends Module {
    private final NumberSetting size = addSetting(new NumberSetting("Size", "Hitbox expansion", 0.3, 0.0, 1.0, 0.05));

    public Hitbox() {
        super("Hitbox", "Expand entity hitboxes", Category.COMBAT);
    }

    public double getExpansion() { return size.getValue(); }
    // Requires mixin on Entity.getBoundingBox to expand by getExpansion()
}
