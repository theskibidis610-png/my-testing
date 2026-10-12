package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class TargetHUD extends Module {
    private final BooleanSetting health = addSetting(new BooleanSetting("Health", "Show target health bar", true));
    private final BooleanSetting armor = addSetting(new BooleanSetting("Armor", "Show target armor value", true));

    public TargetHUD() {
        super("TargetHUD", "Show information about combat target", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Render target info panel via HudRenderCallback
    }

    public boolean showHealth() { return health.getValue(); }
    public boolean showArmor() { return armor.getValue(); }
}
