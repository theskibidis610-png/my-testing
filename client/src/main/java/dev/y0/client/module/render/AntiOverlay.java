package dev.y0.client.module.render;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class AntiOverlay extends Module {
    private final BooleanSetting fire = addSetting(new BooleanSetting("Fire", "Remove fire overlay", true));
    private final BooleanSetting water = addSetting(new BooleanSetting("Water", "Remove water overlay", true));
    private final BooleanSetting pumpkin = addSetting(new BooleanSetting("Pumpkin", "Remove pumpkin overlay", true));

    public AntiOverlay() {
        super("AntiOverlay", "Remove fire/water/pumpkin overlays", Category.RENDER);
    }

    public boolean shouldRemoveFire() {
        return isEnabled() && fire.getValue();
    }

    public boolean shouldRemoveWater() {
        return isEnabled() && water.getValue();
    }

    public boolean shouldRemovePumpkin() {
        return isEnabled() && pumpkin.getValue();
    }
}
