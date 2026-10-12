package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class FPSDisplay extends Module {
    private final BooleanSetting graph = addSetting(new BooleanSetting("Graph", "Show FPS graph overlay", false));

    public FPSDisplay() {
        super("FPSDisplay", "Display FPS counter on HUD", Category.MISC);
    }

    @EventHandler
    public void onTick(EventTick event) {
        // FPS rendering is handled in the HUD render event
    }

    public int getCurrentFPS() {
        return mc.getCurrentFps();
    }

    public boolean showGraph() {
        return graph.getValue();
    }
}
