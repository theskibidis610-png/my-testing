package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;

public class AutoTPA extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "How to handle TPA requests", "Accept", "Accept", "Deny"));

    public AutoTPA() {
        super("AutoTPA", "Automatically accept or deny TPA requests", Category.MISC);
    }

    // NOTE: A full implementation would hook into incoming chat messages
    // to detect TPA request patterns and respond with /tpaccept or /tpdeny.

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.getNetworkHandler() == null) return;
        // Placeholder: detect TPA requests from chat and respond based on mode.
    }

    public String getCommand() {
        return switch (mode.getValue()) {
            case "Accept" -> "/tpaccept";
            case "Deny" -> "/tpdeny";
            default -> "/tpdeny";
        };
    }
}
