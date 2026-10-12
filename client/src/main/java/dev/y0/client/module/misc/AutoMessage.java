package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class AutoMessage extends Module {
    private final NumberSetting interval = addSetting(new NumberSetting("Interval", "Ticks between messages", 600.0, 100.0, 6000.0, 20.0));

    private int tickCounter;

    // Message to send. A full implementation would use a StringSetting or list.
    private static final String MESSAGE = "Hello from Y0 Client!";

    public AutoMessage() {
        super("AutoMessage", "Send messages on a timer", Category.MISC);
    }

    @Override
    protected void onEnable() {
        tickCounter = 0;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.getNetworkHandler() == null) return;

        tickCounter++;
        if (tickCounter >= (int) interval.getValue()) {
            mc.player.networkHandler.sendChatMessage(MESSAGE);
            tickCounter = 0;
        }
    }
}
