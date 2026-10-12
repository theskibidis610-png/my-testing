package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class Spammer extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Ticks between messages", 100.0, 20.0, 200.0, 1.0));

    private int tickCounter;

    // The message to spam. In a full implementation this would be a StringSetting.
    private static final String MESSAGE = "Y0 Client";

    public Spammer() {
        super("Spammer", "Send repeated chat messages", Category.MISC);
    }

    @Override
    protected void onEnable() {
        tickCounter = 0;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.getNetworkHandler() == null) return;

        tickCounter++;

        if (tickCounter >= (int) delay.getValue()) {
            mc.player.networkHandler.sendChatMessage(MESSAGE);
            tickCounter = 0;
        }
    }
}
