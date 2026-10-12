package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class FastBreak extends Module {
    private final NumberSetting multiplier = addSetting(new NumberSetting("Speed", "Break speed multiplier", 1.5, 1.0, 5.0, 0.1));

    public FastBreak() {
        super("FastBreak", "Break blocks faster", Category.PLAYER);
    }

    // Requires a mixin on ClientPlayerInteractionManager.updateBlockBreakingProgress
    // to multiply the breaking progress by the speed setting.
}
