package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class DamageNumbers extends Module {
    private final BooleanSetting color = addSetting(new BooleanSetting("Color", "Color numbers by damage type", true));

    public DamageNumbers() {
        super("DamageNumbers", "Show floating damage numbers on hit", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null) return;
        // Track entity health changes and spawn floating text particles
    }

    public boolean useColor() {
        return color.getValue();
    }
}
