package dev.y0.client.module.render;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;

public class NoFog extends Module {
    private final ModeSetting type = addSetting(new ModeSetting("Type", "Fog type to remove", "All", "All", "Lava", "Water", "Powder"));

    public NoFog() {
        super("NoFog", "Simple fog removal", Category.RENDER);
    }

    public String getFogType() {
        return type.getValue();
    }

    public boolean shouldRemove(String fogType) {
        if (!isEnabled()) return false;
        String selected = type.getValue();
        return selected.equals("All") || selected.equalsIgnoreCase(fogType);
    }
}
