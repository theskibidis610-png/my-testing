package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.Hand;

public class FastUse extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Ticks between uses", 0.0, 0.0, 10.0, 1.0));
    private int tickCounter;

    public FastUse() {
        super("FastUse", "Speeds up item usage by reducing cooldown", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.interactionManager == null) return;
        if (!mc.options.useKey.isPressed()) return;

        tickCounter++;
        if (tickCounter >= delay.getValue()) {
            mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
            tickCounter = 0;
        }
    }
}
