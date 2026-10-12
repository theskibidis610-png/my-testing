package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.text.Text;

public class CoordsLog extends Module {
    private final NumberSetting interval = addSetting(new NumberSetting("Interval", "Ticks between logs", 200.0, 20.0, 1200.0, 20.0));
    private int tickCounter;

    public CoordsLog() {
        super("CoordsLog", "Periodically logs your coordinates to chat", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        tickCounter++;
        if (tickCounter >= interval.getValue()) {
            tickCounter = 0;
            String coords = String.format("XYZ: %.1f / %.1f / %.1f",
                    mc.player.getX(), mc.player.getY(), mc.player.getZ());
            mc.player.sendMessage(Text.literal(coords), true);
        }
    }
}
