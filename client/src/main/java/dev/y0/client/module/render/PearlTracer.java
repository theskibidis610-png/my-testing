package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class PearlTracer extends Module {
    private final BooleanSetting showLanding = addSetting(new BooleanSetting("ShowLanding", "Show predicted landing position", true));

    public PearlTracer() {
        super("PearlTracer", "Trace ender pearl trajectories", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null) return;
        // Draw lines following ender pearl entities in the world
    }

    public boolean showLanding() {
        return showLanding.getValue();
    }
}
