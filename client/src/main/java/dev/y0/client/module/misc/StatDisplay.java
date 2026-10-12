package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class StatDisplay extends Module {
    private final BooleanSetting kills = addSetting(new BooleanSetting("Kills", "Show kill count on HUD", true));

    public StatDisplay() {
        super("StatDisplay", "Display player statistics on HUD", Category.MISC);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Stats are read from the player's StatHandler and rendered on HUD.
    }

    public boolean showKills() {
        return kills.getValue();
    }
}
