package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

public class FastClimb extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Climb speed", 0.5, 0.1, 1.0, 0.05));

    public FastClimb() {
        super("FastClimb", "Climb ladders and vines faster", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (mc.player.isClimbing()) {
            Vec3d vel = mc.player.getVelocity();
            mc.player.setVelocity(vel.x, speed.getValue(), vel.z);
        }
    }
}
