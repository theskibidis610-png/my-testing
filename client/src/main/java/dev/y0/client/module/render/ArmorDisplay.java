package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class ArmorDisplay extends Module {
    private final BooleanSetting durability = addSetting(new BooleanSetting("Durability", "Show remaining durability", true));

    public ArmorDisplay() {
        super("ArmorDisplay", "Show armor info on HUD", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Armor rendering handled via HudRenderCallback
    }

    public boolean showDurability() {
        return durability.getValue();
    }
}
