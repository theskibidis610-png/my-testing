package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.screen.slot.SlotActionType;

public class SpeedSwap extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Ticks between swaps", 1.0, 0.0, 10.0, 1.0));
    private int tickCounter;

    public SpeedSwap() {
        super("SpeedSwap", "Fast item swapping in inventory", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.interactionManager == null) return;
        if (mc.currentScreen == null) return;

        tickCounter++;
        if (tickCounter >= delay.getValue()) {
            tickCounter = 0;
            // Speed swap reduces the click delay on inventory interactions
        }
    }
}
