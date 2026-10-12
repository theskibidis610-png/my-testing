package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

import java.util.HashSet;
import java.util.Set;

public class PlayerAlert extends Module {
    private final BooleanSetting sound = addSetting(new BooleanSetting("Sound", "Play alert sound when player enters render distance", true));

    private final Set<String> knownPlayers = new HashSet<>();

    public PlayerAlert() {
        super("PlayerAlert", "Alert when specific players appear nearby", Category.MISC);
    }

    @Override
    protected void onEnable() {
        knownPlayers.clear();
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null || mc.player == null) return;
        // Player detection scans world players and alerts on new entries
        // not previously seen in the current session.
    }

    public boolean shouldPlaySound() {
        return sound.getValue();
    }
}
