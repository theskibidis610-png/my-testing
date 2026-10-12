package dev.y0.client.module.render;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class LowFire extends Module {
    private final NumberSetting offset = addSetting(new NumberSetting("Offset", "Fire overlay vertical offset", 0.5, 0.0, 1.0, 0.05));

    public LowFire() {
        super("LowFire", "Lower fire overlay position", Category.RENDER);
    }

    public double getOffset() {
        return offset.getValue();
    }
}
