package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

/**
 * Building assistance module. Shows ghost block outlines for placement guidance
 * and provides placement helpers.
 */
public class BuildHelper extends Module {
    private final BooleanSetting ghostBlocks = addSetting(new BooleanSetting("GhostBlocks", "Show ghost block outlines for placement", true));
    private final BooleanSetting autoAlign = addSetting(new BooleanSetting("AutoAlign", "Align block placement to grid", false));

    public BuildHelper() {
        super("BuildHelper", "Building assistance with ghost blocks and alignment", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        // Ghost block rendering is handled via a render event or mixin.
        // This tick handler manages the state for the render overlay.
    }

    public boolean showGhostBlocks() {
        return isEnabled() && ghostBlocks.getValue();
    }

    public boolean shouldAutoAlign() {
        return isEnabled() && autoAlign.getValue();
    }
}
