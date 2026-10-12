package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class AntiLag extends Module {
    private final BooleanSetting limitEntities = addSetting(new BooleanSetting("Limit Entities", "Cap rendered entities to reduce lag", true));

    private static final int MAX_ENTITIES = 200;

    public AntiLag() {
        super("AntiLag", "Reduce client-side lag with various measures", Category.MISC);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null || !limitEntities.getValue()) return;
        // Entity limiting is handled at render time via mixin;
        // this tick handler can be used for additional lag reduction logic.
    }

    public int getMaxEntities() {
        return limitEntities.getValue() ? MAX_ENTITIES : Integer.MAX_VALUE;
    }
}
