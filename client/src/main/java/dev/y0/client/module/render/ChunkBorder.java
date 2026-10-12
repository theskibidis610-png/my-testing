package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class ChunkBorder extends Module {
    private final BooleanSetting grid = addSetting(new BooleanSetting("Grid", "Show full chunk grid lines", false));

    public ChunkBorder() {
        super("ChunkBorder", "Show chunk boundaries", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Render chunk border lines at current chunk edges
    }

    public boolean showGrid() {
        return grid.getValue();
    }
}
