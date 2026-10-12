package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class AutoRelog extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Seconds to wait before reconnecting", 5.0, 1.0, 60.0, 1.0));

    public AutoRelog() {
        super("AutoRelog", "Automatically reconnect after being kicked", Category.MISC);
    }

    @Override
    protected void onEnable() {
        // Reconnection logic is handled by mixin on DisconnectedScreen
        // to auto-click the reconnect button after the delay period.
        Y0Client.LOGGER.info("AutoRelog enabled - will reconnect after {} seconds", (int) delay.getValue());
    }

    @EventHandler
    public void onTick(EventTick event) {
        // Tick handler reserved for checking disconnect state
    }

    public int getDelayTicks() {
        return (int) delay.getValue() * 20;
    }
}
