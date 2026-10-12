package dev.y0.client.module.render;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class Aspect extends Module {
    private final NumberSetting ratio = addSetting(new NumberSetting("Ratio", "Custom aspect ratio", 1.78, 0.5, 3.0, 0.01));

    public Aspect() {
        super("Aspect", "Custom aspect ratio", Category.RENDER);
    }

    public double getRatio() {
        return ratio.getValue();
    }
}
