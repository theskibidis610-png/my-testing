package dev.y0.client.module.render;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import dev.y0.client.setting.NumberSetting;

public class Tracers extends Module {
    private final BooleanSetting players = addSetting(new BooleanSetting("Players", "Draw lines to players", true));
    private final BooleanSetting mobs = addSetting(new BooleanSetting("Mobs", "Draw lines to mobs", false));
    private final NumberSetting lineWidth = addSetting(new NumberSetting("Width", "Line thickness", 1.5, 0.5, 5.0, 0.5));

    public Tracers() {
        super("Tracers", "Draws lines to entities", Category.RENDER);
    }

    // Requires WorldRenderEvent mixin to draw lines from crosshair to entities.
    // Settings and framework are ready; add GL line drawing in a render mixin.
}
