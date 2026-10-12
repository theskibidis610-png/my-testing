package dev.y0.client.module.render;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import dev.y0.client.setting.NumberSetting;

public class Chams extends Module {
    private final BooleanSetting players = addSetting(new BooleanSetting("Players", "Apply chams to players", true));
    private final BooleanSetting mobs = addSetting(new BooleanSetting("Mobs", "Apply chams to mobs", false));
    private final NumberSetting alpha = addSetting(new NumberSetting("Alpha", "Overlay transparency", 128.0, 0.0, 255.0, 1.0));

    public Chams() {
        super("Chams", "See entities through walls with color overlay", Category.RENDER);
    }

    // Requires a mixin on EntityRenderer or LivingEntityRenderer to:
    // 1. Disable depth testing before rendering the entity model
    // 2. Apply a colored overlay with the configured alpha
    // 3. Re-enable depth testing and render normally on top
    // Settings and framework are ready.

    public boolean shouldApplyToPlayers() {
        return isEnabled() && players.getValue();
    }

    public boolean shouldApplyToMobs() {
        return isEnabled() && mobs.getValue();
    }

    public int getAlpha() {
        return (int) alpha.getValue();
    }
}
