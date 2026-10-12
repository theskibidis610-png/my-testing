package dev.y0.client.module.misc;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class ExtraTab extends Module {
    private final NumberSetting maxEntries = addSetting(new NumberSetting("Max Entries", "Maximum player entries in tab list", 200.0, 80.0, 1000.0, 10.0));

    public ExtraTab() {
        super("ExtraTab", "Extended tab list with more entries", Category.MISC);
    }

    // NOTE: This module requires a mixin on net.minecraft.client.gui.hud.PlayerListHud
    // to override the default 80-player limit in the render method.
    // The mixin should replace the hardcoded limit with getMaxEntries().

    public int getMaxEntries() {
        return (int) maxEntries.getValue();
    }
}
