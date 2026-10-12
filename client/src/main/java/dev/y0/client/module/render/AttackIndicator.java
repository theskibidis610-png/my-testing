package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;

public class AttackIndicator extends Module {
    private final ModeSetting style = addSetting(new ModeSetting("Style", "Indicator style", "Bar", "Bar", "Circle", "None"));

    public AttackIndicator() {
        super("AttackIndicator", "Custom attack cooldown indicator", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Render indicator based on mc.player.getAttackCooldownProgress(0)
    }

    public String getStyle() {
        return style.getValue();
    }
}
