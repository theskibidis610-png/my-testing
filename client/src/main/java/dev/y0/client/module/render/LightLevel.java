package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class LightLevel extends Module {
    private final NumberSetting threshold = addSetting(new NumberSetting("Threshold", "Light level warning threshold", 7.0, 0.0, 15.0, 1.0));

    public LightLevel() {
        super("LightLevel", "Show light level overlay on blocks", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null || mc.player == null) return;
        // Render colored numbers on nearby block surfaces indicating light level
    }

    public int getThreshold() {
        return (int) threshold.getValue();
    }
}
