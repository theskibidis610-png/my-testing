package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.text.Text;

public class PingAlert extends Module {
    private final NumberSetting threshold = addSetting(new NumberSetting("Threshold", "Ping threshold in ms", 200.0, 50.0, 1000.0, 10.0));
    private int cooldown;

    public PingAlert() {
        super("PingAlert", "Alerts when ping exceeds threshold", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.getNetworkHandler() == null) return;

        if (cooldown > 0) { cooldown--; return; }

        var entry = mc.getNetworkHandler().getPlayerListEntry(mc.player.getUuid());
        if (entry != null && entry.getLatency() > threshold.getValue()) {
            mc.player.sendMessage(Text.literal("High ping: " + entry.getLatency() + "ms"), true);
            cooldown = 100;
        }
    }
}
