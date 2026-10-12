package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;

public class BlockHighlight extends Module {
    private final ModeSetting type = addSetting(new ModeSetting("Type", "Block type to highlight", "Ores", "Ores", "Spawners", "Portals"));

    public BlockHighlight() {
        super("BlockHighlight", "Highlight specific block types", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null) return;
        // Block scanning and highlight rendering applied via render mixin
    }

    public String getType() {
        return type.getValue();
    }
}
