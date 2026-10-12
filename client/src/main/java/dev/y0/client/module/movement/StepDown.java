package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class StepDown extends Module {
    private final NumberSetting height = addSetting(new NumberSetting("Height", "Step down height in blocks", 2.0, 1.0, 5.0, 0.5));

    public StepDown() {
        super("StepDown", "Step down from blocks smoothly", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.isOnGround() && mc.player.getVelocity().y <= 0) {
            mc.player.stepHeight = (float) height.getValue();
        }
    }

    @Override
    protected void onDisable() {
        if (mc.player != null) {
            mc.player.stepHeight = 0.6f;
        }
    }
}
