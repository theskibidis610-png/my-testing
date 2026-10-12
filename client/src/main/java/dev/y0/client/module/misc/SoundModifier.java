package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class SoundModifier extends Module {
    private final NumberSetting volume = addSetting(new NumberSetting("Volume", "Master volume multiplier", 1.0, 0.0, 3.0, 0.1));

    public SoundModifier() {
        super("SoundModifier", "Modify game sound volumes", Category.MISC);
    }

    @EventHandler
    public void onTick(EventTick event) {
        // Volume modification is applied via mixin on SoundSystem
        // to scale all sound events by the configured multiplier.
    }

    public double getVolumeMultiplier() {
        return volume.getValue();
    }
}
