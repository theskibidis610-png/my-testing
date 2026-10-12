package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class FluidLevel extends Module {
    private final BooleanSetting water = addSetting(new BooleanSetting("Water", "Show water fluid levels", true));
    private final BooleanSetting lava = addSetting(new BooleanSetting("Lava", "Show lava fluid levels", true));

    public FluidLevel() {
        super("FluidLevel", "Show fluid level overlay on liquids", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null) return;
        // Render fluid level numbers on targeted fluid blocks
    }

    public boolean showWater() { return water.getValue(); }
    public boolean showLava() { return lava.getValue(); }
}
