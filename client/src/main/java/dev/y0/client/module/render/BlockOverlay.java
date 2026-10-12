package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import dev.y0.client.setting.NumberSetting;

public class BlockOverlay extends Module {
    private final NumberSetting lineWidth = addSetting(new NumberSetting("Line Width", "Outline thickness", 2.0, 1.0, 5.0, 0.5));
    private final BooleanSetting fill = addSetting(new BooleanSetting("Fill", "Fill the block selection box", false));

    public BlockOverlay() {
        super("BlockOverlay", "Custom block selection overlay", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        // Placeholder: the actual overlay rendering requires a mixin on
        // WorldRenderer.drawBlockOutline to replace the default outline
        // with custom line width and optional fill. Settings are ready.
    }

    public double getLineWidth() {
        return lineWidth.getValue();
    }

    public boolean shouldFill() {
        return fill.getValue();
    }
}
