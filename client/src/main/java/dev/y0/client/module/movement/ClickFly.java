package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class ClickFly extends Module {
    private final BooleanSetting hold = addSetting(new BooleanSetting("Hold", "Hold click to fly instead of toggle", false));

    public ClickFly() {
        super("ClickFly", "Fly while clicking", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        boolean shouldFly = hold.getValue() ? mc.options.useKey.isPressed() : mc.player.getAbilities().flying;
        if (mc.options.useKey.wasPressed() && !hold.getValue()) {
            mc.player.getAbilities().flying = !mc.player.getAbilities().flying;
        }
        if (shouldFly) {
            mc.player.getAbilities().flying = true;
            mc.player.getAbilities().setFlySpeed(0.05f);
        }
    }
}
