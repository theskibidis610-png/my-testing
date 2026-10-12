package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class AutoReconnect extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Seconds before reconnecting", 5.0, 1.0, 30.0, 1.0));

    private boolean shouldReconnect;
    private long disconnectTime;

    public AutoReconnect() {
        super("AutoReconnect", "Automatically reconnect on disconnect", Category.MISC);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player != null) {
            // We are connected, reset reconnect state
            shouldReconnect = false;
            return;
        }

        // NOTE: Full implementation requires a mixin on DisconnectedScreen
        // to capture the last server info and trigger reconnection after the delay.
        // The mixin would store the ServerInfo and re-initiate ConnectScreen.connect()
        // after delay.getValue() seconds have elapsed since disconnect.

        if (shouldReconnect && System.currentTimeMillis() - disconnectTime >= (long)(delay.getValue() * 1000)) {
            Y0Client.LOGGER.info("AutoReconnect: Attempting to reconnect...");
            shouldReconnect = false;
            // Reconnection logic would go here via stored ServerInfo
        }
    }

    @Override
    protected void onDisable() {
        // Store reconnect intent so we know to reconnect when re-enabled
        if (mc.player == null) {
            shouldReconnect = true;
            disconnectTime = System.currentTimeMillis();
        }
    }

    @Override
    protected void onEnable() {
        if (mc.player == null) {
            shouldReconnect = true;
            disconnectTime = System.currentTimeMillis();
        }
    }
}
