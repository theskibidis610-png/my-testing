package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class PositionLog extends Module {
    private final NumberSetting interval = addSetting(new NumberSetting("Interval", "Ticks between position logs", 200.0, 20.0, 1200.0, 20.0));

    private int tickCounter;

    public PositionLog() {
        super("PositionLog", "Periodically log player position", Category.MISC);
    }

    @Override
    protected void onEnable() {
        tickCounter = 0;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        tickCounter++;
        if (tickCounter >= (int) interval.getValue()) {
            Y0Client.LOGGER.info("Position: {}, {}, {}",
                    String.format("%.1f", mc.player.getX()),
                    String.format("%.1f", mc.player.getY()),
                    String.format("%.1f", mc.player.getZ()));
            tickCounter = 0;
        }
    }
}
