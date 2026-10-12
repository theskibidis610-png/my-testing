package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class MobSpawnESP extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Scan range for spawn spots", 32.0, 8.0, 64.0, 4.0));

    public MobSpawnESP() {
        super("MobSpawnESP", "Highlight blocks where mobs can spawn", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null || mc.player == null) return;
        // Check light levels on spawnable blocks and render overlay
    }

    public double getRange() {
        return range.getValue();
    }
}
