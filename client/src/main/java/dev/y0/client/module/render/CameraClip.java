package dev.y0.client.module.render;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class CameraClip extends Module {
    private final BooleanSetting extendDistance = addSetting(new BooleanSetting("Extend Distance", "Allow camera further from player", false));

    public CameraClip() {
        super("CameraClip", "Prevent camera clipping into blocks", Category.RENDER);
    }

    // Requires a mixin on Camera.clipToSpace to remove or extend the
    // raycast that pulls the camera forward when it would enter a block.
    // When extendDistance is true, the mixin should also increase the
    // maximum third-person camera distance.

    public boolean shouldExtendDistance() {
        return isEnabled() && extendDistance.getValue();
    }
}
