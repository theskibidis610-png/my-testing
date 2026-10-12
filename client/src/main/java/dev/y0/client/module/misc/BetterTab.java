package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class BetterTab extends Module {
    private final BooleanSetting showPing = addSetting(new BooleanSetting("Show Ping", "Display numeric ping values", true));

    public BetterTab() {
        super("BetterTab", "Enhanced player tab list display", Category.MISC);
    }

    @EventHandler
    public void onTick(EventTick event) {
        // Tab rendering modifications are handled by mixin on PlayerListHud.
        // showPing controls whether numeric ping is shown instead of bars.
    }

    public boolean shouldShowPing() {
        return showPing.getValue();
    }
}
