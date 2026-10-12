package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;

public class DeathAnimation extends Module {
    private final ModeSetting type = addSetting(new ModeSetting("Type", "Death animation style", "Spin", "Spin", "Fade", "None"));

    public DeathAnimation() {
        super("DeathAnimation", "Custom entity death animation", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null) return;
        // Modify death animation via entity render mixin
    }

    public String getType() {
        return type.getValue();
    }
}
