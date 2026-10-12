package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class AutoLeave extends Module {
    private final NumberSetting playerThreshold = addSetting(new NumberSetting("Player Threshold", "Leave when player count exceeds this", 10.0, 1.0, 100.0, 1.0));

    public AutoLeave() {
        super("AutoLeave", "Auto-disconnect when too many players are nearby", Category.MISC);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.getNetworkHandler() == null) return;

        int playerCount = mc.getNetworkHandler().getPlayerList().size();
        if (playerCount > (int) playerThreshold.getValue()) {
            mc.player.networkHandler.getConnection().disconnect(
                    net.minecraft.text.Text.of("AutoLeave: player threshold exceeded")
            );
        }
    }
}
