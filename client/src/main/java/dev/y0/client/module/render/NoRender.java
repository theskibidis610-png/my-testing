package dev.y0.client.module.render;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class NoRender extends Module {
    private final BooleanSetting hurtCam = addSetting(new BooleanSetting("No HurtCam", "Disable screen shake on hit", true));
    private final BooleanSetting fire = addSetting(new BooleanSetting("No Fire", "Hide fire overlay", true));
    private final BooleanSetting water = addSetting(new BooleanSetting("No Water", "Hide underwater overlay", true));
    private final BooleanSetting pumpkin = addSetting(new BooleanSetting("No Pumpkin", "Hide pumpkin overlay", true));
    private final BooleanSetting fog = addSetting(new BooleanSetting("No Fog", "Disable fog rendering", true));
    private final BooleanSetting totem = addSetting(new BooleanSetting("No Totem", "Hide totem animation", true));

    public NoRender() {
        super("NoRender", "Disable visual effects", Category.RENDER);
    }

    // Requires mixins on various render methods to check these settings
    // and cancel the rendering. Settings and framework are ready.

    public boolean shouldHideHurtCam() { return isEnabled() && hurtCam.getValue(); }
    public boolean shouldHideFire() { return isEnabled() && fire.getValue(); }
    public boolean shouldHideWater() { return isEnabled() && water.getValue(); }
    public boolean shouldHidePumpkin() { return isEnabled() && pumpkin.getValue(); }
    public boolean shouldHideFog() { return isEnabled() && fog.getValue(); }
    public boolean shouldHideTotem() { return isEnabled() && totem.getValue(); }
}
