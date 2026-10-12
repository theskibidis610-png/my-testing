package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;

public class AutoCloseChest extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Ticks before closing", 20.0, 1.0, 100.0, 1.0));
    private int tickCounter;

    public AutoCloseChest() {
        super("AutoCloseChest", "Automatically closes containers after a delay", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.currentScreen instanceof GenericContainerScreen) {
            tickCounter++;
            if (tickCounter >= delay.getValue()) {
                mc.player.closeHandledScreen();
                tickCounter = 0;
            }
        } else {
            tickCounter = 0;
        }
    }
}
