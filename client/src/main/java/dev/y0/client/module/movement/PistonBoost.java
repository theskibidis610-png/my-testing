package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

public class PistonBoost extends Module {
    private final NumberSetting multiplier = addSetting(new NumberSetting("Multiplier", "Piston push velocity multiplier", 2.0, 1.0, 5.0, 0.5));

    public PistonBoost() {
        super("PistonBoost", "Get boosted further by pistons", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        Vec3d vel = mc.player.getVelocity();
        double speed = Math.sqrt(vel.x * vel.x + vel.z * vel.z);
        if (speed > 0.5 && !mc.player.isOnGround()) {
            mc.player.setVelocity(vel.x * multiplier.getValue(), vel.y, vel.z * multiplier.getValue());
        }
    }
}
