package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class EntityOwner extends Module {
    private final BooleanSetting uuid = addSetting(new BooleanSetting("UUID", "Show owner UUID instead of name", false));

    public EntityOwner() {
        super("EntityOwner", "Show tameable entity owners", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null) return;
        // Render owner nametag above tamed entities
    }

    public boolean showUuid() {
        return uuid.getValue();
    }
}
