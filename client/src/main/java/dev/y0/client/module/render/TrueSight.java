package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class TrueSight extends Module {
    private final BooleanSetting players = addSetting(new BooleanSetting("Players", "See invisible players", true));
    private final BooleanSetting mobs = addSetting(new BooleanSetting("Mobs", "See invisible mobs", true));

    public TrueSight() {
        super("TrueSight", "See invisible entities", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null) return;
        // Invisible entity rendering handled via entity render mixin
    }

    public boolean showPlayers() { return players.getValue(); }
    public boolean showMobs() { return mobs.getValue(); }
}
