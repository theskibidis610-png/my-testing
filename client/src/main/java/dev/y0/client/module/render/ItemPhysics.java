package dev.y0.client.module.render;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class ItemPhysics extends Module {
    private final NumberSetting rotationSpeed = addSetting(new NumberSetting("Rotation Speed", "How fast dropped items rotate", 1.0, 0.1, 5.0, 0.1));

    public ItemPhysics() {
        super("ItemPhysics", "Realistic item drop physics", Category.RENDER);
    }

    // Requires a mixin on ItemEntityRenderer.render to replace the
    // default floating/spinning animation with physics-based behavior:
    // 1. Items lie flat on the ground instead of hovering
    // 2. Items rotate while falling at rotationSpeed rate
    // 3. Stacked items fan out slightly
    // Settings and framework are ready.

    public double getRotationSpeed() {
        return rotationSpeed.getValue();
    }
}
