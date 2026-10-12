package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class VoidFloat extends Module {
    private final NumberSetting height = addSetting(new NumberSetting("Height", "Minimum Y level to float at", 0.0, -64.0, 64.0, 1.0));

    public VoidFloat() {
        super("VoidFloat", "Float above the void instead of falling", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.getY() <= height.getValue()) {
            mc.player.setVelocity(mc.player.getVelocity().x, 0.0, mc.player.getVelocity().z);
            mc.player.setPosition(mc.player.getX(), height.getValue(), mc.player.getZ());
        }
    }
}
