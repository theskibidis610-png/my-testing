package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class CombatInfo extends Module {
    private final BooleanSetting potions = addSetting(new BooleanSetting("Potions", "Show active potion effects", true));

    public CombatInfo() {
        super("CombatInfo", "Show combat statistics on HUD", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Display health, totems, gaps, and optionally potions on HUD
    }

    public boolean showPotions() {
        return potions.getValue();
    }
}
