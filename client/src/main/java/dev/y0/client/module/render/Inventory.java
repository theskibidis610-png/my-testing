package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class Inventory extends Module {
    private final BooleanSetting armor = addSetting(new BooleanSetting("Armor", "Show armor slots in preview", true));

    public Inventory() {
        super("Inventory", "Render inventory preview on HUD", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Render player inventory grid on screen via HudRenderCallback
    }

    public boolean showArmor() {
        return armor.getValue();
    }
}
