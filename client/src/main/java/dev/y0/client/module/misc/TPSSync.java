package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class TPSSync extends Module {
    private final NumberSetting targetTPS = addSetting(new NumberSetting("Target TPS", "Server TPS to sync to", 20.0, 1.0, 20.0, 0.5));

    private long lastTickTime;
    private double serverTPS = 20.0;

    public TPSSync() {
        super("TPSSync", "Sync movement speed to server TPS", Category.MISC);
    }

    @Override
    protected void onEnable() {
        lastTickTime = System.currentTimeMillis();
        serverTPS = 20.0;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        long now = System.currentTimeMillis();
        long delta = now - lastTickTime;
        lastTickTime = now;

        // Estimate server TPS from tick intervals
        if (delta > 0) {
            double measuredTPS = 1000.0 / delta;
            serverTPS = serverTPS * 0.8 + Math.min(measuredTPS, 20.0) * 0.2;
        }
    }

    public double getMultiplier() {
        return serverTPS / targetTPS.getValue();
    }

    public double getServerTPS() {
        return serverTPS;
    }
}
