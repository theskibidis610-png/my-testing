package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class BackStep extends Module {
    private final NumberSetting distance = addSetting(new NumberSetting("Distance", "Backstep distance", 2.0, 0.5, 5.0, 0.5));

    public BackStep() {
        super("BackStep", "Quick backstep away from current direction", Category.MOVEMENT);
    }

    @Override
    protected void onEnable() {
        if (mc.player == null) return;
        double yaw = Math.toRadians(mc.player.getYaw());
        mc.player.setVelocity(
                Math.sin(yaw) * distance.getValue(),
                0.2,
                -Math.cos(yaw) * distance.getValue()
        );
        toggle();
    }

    @EventHandler
    public void onTick(EventTick event) {}
}
