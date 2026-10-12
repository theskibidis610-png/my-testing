package dev.y0.client.module.combat;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class BackTrack extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Backtrack delay (ms)", 200.0, 50.0, 500.0, 10.0));

    public BackTrack() {
        super("BackTrack", "Delay incoming packets for extended reach", Category.COMBAT);
    }

    // Requires a packet handler mixin to delay incoming entity position packets.
}
