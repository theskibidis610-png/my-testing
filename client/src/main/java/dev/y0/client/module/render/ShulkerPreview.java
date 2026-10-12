package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class ShulkerPreview extends Module {
    private final BooleanSetting tooltip = addSetting(new BooleanSetting("Tooltip", "Show contents in item tooltip", true));

    public ShulkerPreview() {
        super("ShulkerPreview", "Preview shulker box contents on hover", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Shulker tooltip rendering handled via item tooltip mixin
    }

    public boolean showTooltip() {
        return tooltip.getValue();
    }
}
