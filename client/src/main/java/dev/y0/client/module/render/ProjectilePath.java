package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class ProjectilePath extends Module {
    private final BooleanSetting bow = addSetting(new BooleanSetting("Bow", "Show arrow trajectory", true));
    private final BooleanSetting pearl = addSetting(new BooleanSetting("Pearl", "Show ender pearl trajectory", true));

    public ProjectilePath() {
        super("ProjectilePath", "Show predicted projectile trajectory", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Calculate and render projectile arc via world render event
    }

    public boolean showBow() { return bow.getValue(); }
    public boolean showPearl() { return pearl.getValue(); }
}
