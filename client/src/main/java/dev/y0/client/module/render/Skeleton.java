package dev.y0.client.module.render;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class Skeleton extends Module {
    private final BooleanSetting self = addSetting(new BooleanSetting("Self", "Show own skeleton", false));
    private final BooleanSetting others = addSetting(new BooleanSetting("Others", "Show other player skeletons", true));

    public Skeleton() {
        super("Skeleton", "Show player skeletons", Category.RENDER);
    }

    public boolean shouldRenderSelf() {
        return isEnabled() && self.getValue();
    }

    public boolean shouldRenderOthers() {
        return isEnabled() && others.getValue();
    }
}
