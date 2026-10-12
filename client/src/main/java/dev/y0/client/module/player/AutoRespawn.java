package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class AutoRespawn extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Ticks before respawning", 0.0, 0.0, 100.0, 1.0));

    private int deathTicks;

    public AutoRespawn() {
        super("AutoRespawn", "Automatically respawn on death", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (mc.player.isDead()) {
            deathTicks++;
            if (deathTicks > delay.getValue()) {
                mc.player.requestRespawn();
                mc.setScreen(null);
                deathTicks = 0;
            }
        } else {
            deathTicks = 0;
        }
    }
}
