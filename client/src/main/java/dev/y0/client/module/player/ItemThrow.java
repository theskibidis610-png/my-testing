package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class ItemThrow extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Ticks between throws", 1.0, 0.0, 20.0, 1.0));
    private int tickCounter;

    public ItemThrow() {
        super("ItemThrow", "Rapidly throws items from your hand", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.getMainHandStack().isEmpty()) return;

        tickCounter++;
        if (tickCounter >= delay.getValue()) {
            mc.player.dropSelectedItem(false);
            tickCounter = 0;
        }
    }
}
