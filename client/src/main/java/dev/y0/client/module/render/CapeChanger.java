package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;

public class CapeChanger extends Module {
    private final ModeSetting cape = addSetting(new ModeSetting("Cape", "Cape type to display", "None", "None", "Minecon", "OptiFine"));

    public CapeChanger() {
        super("CapeChanger", "Custom cape display on your player", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Cape texture swapping handled via player render mixin
    }

    public String getCape() {
        return cape.getValue();
    }
}
