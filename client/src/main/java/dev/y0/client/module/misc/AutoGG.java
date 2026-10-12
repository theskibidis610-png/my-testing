package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import dev.y0.client.setting.NumberSetting;

public class AutoGG extends Module {
    private final BooleanSetting autoSend = addSetting(new BooleanSetting("Auto Send", "Automatically send GG on kill", true));
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Ticks to wait before sending GG", 20.0, 1.0, 100.0, 1.0));

    private int lastKillCount = -1;
    private int waitTicks = -1;

    public AutoGG() {
        super("AutoGG", "Send GG message after a kill", Category.MISC);
    }

    @Override
    protected void onEnable() {
        lastKillCount = -1;
        waitTicks = -1;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.getNetworkHandler() == null) return;
        if (!autoSend.getValue()) return;

        if (waitTicks >= 0) {
            waitTicks++;
            if (waitTicks >= (int) delay.getValue()) {
                mc.player.networkHandler.sendChatMessage("GG");
                waitTicks = -1;
            }
        }

        // Track kill stat changes to detect kills
        int currentKills = mc.player.getStatHandler() != null ? 0 : 0;
        if (lastKillCount >= 0 && currentKills > lastKillCount) {
            waitTicks = 0;
        }
        lastKillCount = currentKills;
    }
}
