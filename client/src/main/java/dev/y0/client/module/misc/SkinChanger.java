package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;

public class SkinChanger extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Skin model to display", "Steve", "Steve", "Alex", "Custom"));

    public SkinChanger() {
        super("SkinChanger", "Change your displayed skin model", Category.MISC);
    }

    @Override
    protected void onEnable() {
        Y0Client.LOGGER.info("SkinChanger enabled - mode: {}", mode.getValue());
    }

    // NOTE: Changing the displayed skin requires a mixin on
    // AbstractClientPlayerEntity.getSkinTextures() to override
    // the skin model type based on the selected mode.
    // "Custom" mode would load a skin from a local file or URL.

    public String getSelectedMode() {
        return mode.getValue();
    }

    public boolean isSlim() {
        return "Alex".equals(mode.getValue());
    }
}
