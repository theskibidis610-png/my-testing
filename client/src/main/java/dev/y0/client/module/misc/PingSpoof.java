package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class PingSpoof extends Module {
    private final NumberSetting ping = addSetting(new NumberSetting("Ping", "Spoofed ping value in ms", 200.0, 1.0, 1000.0, 1.0));

    private int tickCounter;

    public PingSpoof() {
        super("PingSpoof", "Spoof your ping value", Category.MISC);
    }

    @Override
    protected void onEnable() {
        tickCounter = 0;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.getNetworkHandler() == null) return;

        // Delay KeepAlive responses to simulate higher ping.
        // A full implementation would use a mixin on ClientPlayNetworkHandler
        // to intercept KeepAliveS2CPacket and delay the response by
        // the configured ping value in milliseconds.
        tickCounter++;
    }

    public int getSpoofedPing() {
        return (int) ping.getValue();
    }
}
