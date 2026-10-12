package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

public class SneakSlide extends Module {
    private final NumberSetting distance = addSetting(new NumberSetting("Distance", "Slide distance multiplier", 1.5, 0.5, 5.0, 0.5));

    public SneakSlide() {
        super("SneakSlide", "Slide forward while sneaking", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.isSneaking() && mc.player.isOnGround()) {
            Vec3d vel = mc.player.getVelocity();
            double speed = Math.sqrt(vel.x * vel.x + vel.z * vel.z);
            if (speed > 0.01) {
                mc.player.setVelocity(vel.x * distance.getValue(), vel.y, vel.z * distance.getValue());
            }
        }
    }
}
