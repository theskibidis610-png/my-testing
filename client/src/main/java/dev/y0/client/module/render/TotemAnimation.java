package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;

public class TotemAnimation extends Module {
    private final ModeSetting style = addSetting(new ModeSetting("Style", "Totem pop animation style", "Normal", "Normal", "Custom", "None"));

    public TotemAnimation() {
        super("TotemAnimation", "Custom totem of undying pop animation", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Totem animation modified via game renderer mixin
    }

    public String getStyle() {
        return style.getValue();
    }
}
