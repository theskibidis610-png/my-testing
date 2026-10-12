package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class SpawnerESP extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Detection range", 64.0, 16.0, 256.0, 8.0));

    public SpawnerESP() {
        super("SpawnerESP", "Highlight mob spawners through walls", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null) return;
        // Scan for spawner block entities and render highlights
    }

    public double getRange() {
        return range.getValue();
    }
}
