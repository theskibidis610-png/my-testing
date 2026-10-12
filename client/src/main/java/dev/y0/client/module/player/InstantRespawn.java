package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.client.gui.screen.DeathScreen;

public class InstantRespawn extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Ticks before respawning", 0.0, 0.0, 20.0, 1.0));
    private int tickCounter;

    public InstantRespawn() {
        super("InstantRespawn", "Instantly respawns after death", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (mc.currentScreen instanceof DeathScreen) {
            tickCounter++;
            if (tickCounter >= delay.getValue()) {
                mc.player.requestRespawn();
                mc.setScreen(null);
                tickCounter = 0;
            }
        } else {
            tickCounter = 0;
        }
    }
}
