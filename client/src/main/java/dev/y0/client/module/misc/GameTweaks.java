package dev.y0.client.module.misc;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class GameTweaks extends Module {
    private final BooleanSetting noRainSound = addSetting(new BooleanSetting("No Rain Sound", "Disable rain ambient sound", true));
    private final BooleanSetting fastContainerClose = addSetting(new BooleanSetting("Fast Container Close", "Instantly close containers", false));

    public GameTweaks() {
        super("GameTweaks", "Various quality-of-life game tweaks", Category.MISC);
    }

    // NOTE: No Rain Sound requires a mixin on the sound system to cancel
    // rain ambient sounds (weather.rain, weather.rain.above).
    // Fast Container Close requires a mixin on HandledScreen to send
    // the close packet immediately without the closing animation.

    public boolean shouldSuppressRainSound() {
        return isEnabled() && noRainSound.getValue();
    }

    public boolean shouldFastClose() {
        return isEnabled() && fastContainerClose.getValue();
    }
}
