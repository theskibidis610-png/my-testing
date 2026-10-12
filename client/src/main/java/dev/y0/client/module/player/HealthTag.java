package dev.y0.client.module.player;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class HealthTag extends Module {
    private final BooleanSetting show = addSetting(new BooleanSetting("Show", "Show health above player nametags", true));

    public HealthTag() {
        super("HealthTag", "Displays health values above other players", Category.PLAYER);
    }

    public boolean shouldShow() {
        return isEnabled() && show.getValue();
    }

    // Rendering handled via mixin on EntityRenderer.renderLabelIfPresent
    // to append health text when shouldShow() returns true.
}
