package dev.y0.client.module.movement;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class NoSlow extends Module {
    private final BooleanSetting items = addSetting(new BooleanSetting("Items", "No slowdown from using items", true));
    private final BooleanSetting webs = addSetting(new BooleanSetting("Webs", "No slowdown from cobwebs", true));
    private final BooleanSetting soulSand = addSetting(new BooleanSetting("Soul Sand", "No slowdown from soul sand", true));
    private final BooleanSetting berryBush = addSetting(new BooleanSetting("Berry Bush", "No slowdown from sweet berry bushes", true));

    public NoSlow() {
        super("NoSlow", "Removes movement slowdowns", Category.MOVEMENT);
    }

    // Requires mixins on:
    // - ItemUseMixin to prevent item use slowdown
    // - CobwebMixin / BlockCollisionMixin for web/soulsand/berry
}
