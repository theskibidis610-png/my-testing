package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import dev.y0.client.setting.NumberSetting;

public class NameTags extends Module {
    private final BooleanSetting health = addSetting(new BooleanSetting("Health", "Show player health", true));
    private final BooleanSetting armor = addSetting(new BooleanSetting("Armor", "Show armor value", true));
    private final BooleanSetting scaleSetting = addSetting(new BooleanSetting("Scale", "Scale nametags with distance", true));
    private final NumberSetting scaleFactor = addSetting(new NumberSetting("Scale Factor", "Base nametag size multiplier", 2.0, 0.5, 5.0, 0.5));

    public NameTags() {
        super("NameTags", "Enhanced name tag rendering", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        // Nametag rendering requires a mixin on EntityRenderer.renderLabelIfPresent
        // or a WorldRenderEvent to draw custom nametags with health, armor,
        // and distance-based scaling. This tick handler keeps the module
        // registered on the event bus while active.
    }

    public boolean showHealth() { return health.getValue(); }
    public boolean showArmor() { return armor.getValue(); }
    public boolean shouldScale() { return scaleSetting.getValue(); }
    public double getScaleFactor() { return scaleFactor.getValue(); }
}
