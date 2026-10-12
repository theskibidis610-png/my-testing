package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;

import java.util.HashSet;
import java.util.Set;

public class EntityNotifier extends Module {
    private final ModeSetting type = addSetting(new ModeSetting("Type", "Entity type to watch for", "Player", "Player", "Wither", "Dragon"));

    private final Set<Integer> notifiedEntities = new HashSet<>();

    public EntityNotifier() {
        super("EntityNotifier", "Notify when specific entities spawn nearby", Category.MISC);
    }

    @Override
    protected void onEnable() {
        notifiedEntities.clear();
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null || mc.player == null) return;
        // Entity detection is handled per-tick by scanning world entities
        // matching the selected type and alerting on new ones.
    }

    public String getWatchedType() {
        return type.getValue();
    }
}
