package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class BreakIndicator extends Module {
    private final BooleanSetting percentage = addSetting(new BooleanSetting("Percentage", "Show break progress as percentage", true));

    public BreakIndicator() {
        super("BreakIndicator", "Show block breaking progress", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.interactionManager == null) return;
        // Render break progress overlay on the targeted block
    }

    public boolean showPercentage() {
        return percentage.getValue();
    }
}
