package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

public class ReverseStep extends Module {
    private final NumberSetting height = addSetting(new NumberSetting("Height", "Max fall height", 2.0, 1.0, 5.0, 0.5));

    public ReverseStep() {
        super("ReverseStep", "Fall down blocks instantly", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (mc.player.isOnGround() && !mc.options.jumpKey.isPressed()) {
            Vec3d vel = mc.player.getVelocity();
            mc.player.setVelocity(vel.x, -height.getValue(), vel.z);
        }
    }
}
