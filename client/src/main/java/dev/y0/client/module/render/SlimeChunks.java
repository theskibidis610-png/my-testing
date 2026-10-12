package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class SlimeChunks extends Module {
    private final BooleanSetting border = addSetting(new BooleanSetting("Border", "Show chunk border outline", true));

    public SlimeChunks() {
        super("SlimeChunks", "Highlight slime spawn chunks", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null || mc.player == null) return;
        // Calculate slime chunks using world seed and render overlay
    }

    public boolean showBorder() {
        return border.getValue();
    }
}
