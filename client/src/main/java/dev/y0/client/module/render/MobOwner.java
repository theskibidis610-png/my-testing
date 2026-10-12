package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class MobOwner extends Module {
    private final BooleanSetting showName = addSetting(new BooleanSetting("Show Name", "Display owner name above mob", true));

    public MobOwner() {
        super("MobOwner", "Show tamed mob owners", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null) return;
        // Iterate tamed entities and resolve owner UUIDs to names
        // Nametag rendering handled via entity render mixin
    }

    public boolean shouldShowName() {
        return showName.getValue();
    }
}
