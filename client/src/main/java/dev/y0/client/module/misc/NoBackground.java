package dev.y0.client.module.misc;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class NoBackground extends Module {
    private final NumberSetting opacity = addSetting(new NumberSetting("Opacity", "GUI background opacity (0 = transparent)", 50.0, 0.0, 255.0, 5.0));

    public NoBackground() {
        super("NoBackground", "Make GUI backgrounds transparent", Category.MISC);
    }

    // NOTE: Requires a mixin on Screen.renderBackground() to apply
    // the custom opacity value instead of the default dark overlay.

    public int getOpacity() {
        return (int) opacity.getValue();
    }
}
