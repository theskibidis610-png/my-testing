package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class TNTTimer extends Module {
    private final BooleanSetting showText = addSetting(new BooleanSetting("Show Text", "Display fuse time as text", true));

    public TNTTimer() {
        super("TNTTimer", "Show TNT fuse timer", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null) return;
        // Iterate TNT entities and calculate remaining fuse ticks
        // Rendered as nametag above each TNT entity via render mixin
    }

    public boolean shouldShowText() {
        return showText.getValue();
    }
}
