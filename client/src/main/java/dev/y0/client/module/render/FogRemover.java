package dev.y0.client.module.render;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class FogRemover extends Module {
    private final BooleanSetting removeAll = addSetting(new BooleanSetting("Remove All", "Remove all fog rendering", true));

    public FogRemover() {
        super("FogRemover", "Remove fog rendering", Category.RENDER);
    }

    public boolean shouldRemoveFog() {
        return isEnabled() && removeAll.getValue();
    }
}
