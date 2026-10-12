package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class PotionHUD extends Module {
    private final BooleanSetting duration = addSetting(new BooleanSetting("Duration", "Show remaining duration", true));

    public PotionHUD() {
        super("PotionHUD", "Show active potion effects on HUD", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Render potion effect list via HudRenderCallback
    }

    public boolean showDuration() {
        return duration.getValue();
    }
}
