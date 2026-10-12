package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class AntiAFK extends Module {
    private final NumberSetting interval = addSetting(new NumberSetting("Interval", "Ticks between actions", 200.0, 100.0, 1000.0, 10.0));

    private int tickCounter;

    public AntiAFK() {
        super("AntiAFK", "Prevents being kicked for inactivity", Category.PLAYER);
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
            tickCounter = 0;
            if (mc.player.isOnGround()) {
                mc.player.jump();
            } else {
                mc.player.swingHand(net.minecraft.util.Hand.MAIN_HAND);
            }
        }
    }
}
