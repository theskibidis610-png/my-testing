package dev.y0.client.module.render;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class HandESP extends Module {
    private final BooleanSetting mainHand = addSetting(new BooleanSetting("Main Hand", "Highlight main hand item", true));
    private final BooleanSetting offHand = addSetting(new BooleanSetting("Off Hand", "Highlight off hand item", false));

    public HandESP() {
        super("HandESP", "Highlight held items", Category.RENDER);
    }

    public boolean shouldHighlightMainHand() {
        return isEnabled() && mainHand.getValue();
    }

    public boolean shouldHighlightOffHand() {
        return isEnabled() && offHand.getValue();
    }
}
