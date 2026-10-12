package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class PlayerModel extends Module {
    private final BooleanSetting spinHead = addSetting(new BooleanSetting("SpinHead", "Spin player head continuously", false));

    public PlayerModel() {
        super("PlayerModel", "Custom player model tweaks", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Player model modifications applied via player render mixin
    }

    public boolean shouldSpinHead() {
        return spinHead.getValue();
    }
}
