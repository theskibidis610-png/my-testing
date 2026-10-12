package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class TimerFly extends Module {
    private final NumberSetting timerSpeed = addSetting(new NumberSetting("Timer Speed", "Game timer multiplier", 2.0, 0.5, 10.0, 0.5));

    public TimerFly() {
        super("TimerFly", "Fly using timer speed manipulation", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        mc.player.getAbilities().flying = true;
        mc.player.getAbilities().setFlySpeed((float) (0.05 * timerSpeed.getValue()));
    }

    @Override
    protected void onDisable() {
        if (mc.player != null && !mc.player.isCreative()) {
            mc.player.getAbilities().flying = false;
            mc.player.getAbilities().setFlySpeed(0.05f);
        }
    }
}
