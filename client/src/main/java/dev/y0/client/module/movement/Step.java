package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class Step extends Module {
    private final NumberSetting height = addSetting(new NumberSetting("Height", "Step height in blocks", 1.0, 0.5, 2.5, 0.5));

    private float prevStepHeight;

    public Step() {
        super("Step", "Step up blocks instantly", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        mc.player.setStepHeight((float) height.getValue());
    }

    @Override
    protected void onEnable() {
        if (mc.player != null) {
            prevStepHeight = mc.player.getStepHeight();
        }
    }

    @Override
    protected void onDisable() {
        if (mc.player != null) {
            mc.player.setStepHeight(prevStepHeight);
        }
    }
}
