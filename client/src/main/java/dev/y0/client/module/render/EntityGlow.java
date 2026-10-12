package dev.y0.client.module.render;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class EntityGlow extends Module {
    private final BooleanSetting players = addSetting(new BooleanSetting("Players", "Make players glow", true));
    private final BooleanSetting mobs = addSetting(new BooleanSetting("Mobs", "Make mobs glow", false));
    private final BooleanSetting items = addSetting(new BooleanSetting("Items", "Make items glow", false));

    public EntityGlow() {
        super("EntityGlow", "Make entities glow", Category.RENDER);
    }

    public boolean shouldGlowPlayers() {
        return isEnabled() && players.getValue();
    }

    public boolean shouldGlowMobs() {
        return isEnabled() && mobs.getValue();
    }

    public boolean shouldGlowItems() {
        return isEnabled() && items.getValue();
    }
}
