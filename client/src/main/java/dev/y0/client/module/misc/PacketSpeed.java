package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class PacketSpeed extends Module {
    private final NumberSetting rate = addSetting(new NumberSetting("Rate", "Max packets per tick", 20.0, 1.0, 100.0, 1.0));

    public PacketSpeed() {
        super("PacketSpeed", "Adjust outbound packet send rate", Category.MISC);
    }

    @EventHandler
    public void onTick(EventTick event) {
        // Packet rate limiting handled by mixin on ClientConnection.send()
    }

    public int getMaxRate() {
        return (int) rate.getValue();
    }
}
