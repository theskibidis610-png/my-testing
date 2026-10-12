package dev.y0.client.module.misc;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class NoGUIClose extends Module {
    private final BooleanSetting escape = addSetting(new BooleanSetting("Block Escape", "Prevent Escape key from closing GUIs", true));

    public NoGUIClose() {
        super("NoGUIClose", "Prevent accidental GUI closing", Category.MISC);
    }

    // NOTE: Requires a mixin on Screen.keyPressed() to cancel
    // the Escape key when this module is enabled.

    public boolean shouldBlockEscape() {
        return escape.getValue();
    }
}
