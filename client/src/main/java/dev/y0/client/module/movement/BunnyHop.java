package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

public class BunnyHop extends Module {
    private final NumberSetting boost = addSetting(new NumberSetting("Boost", "Hop speed boost", 1.2, 1.0, 2.0, 0.05));

    public BunnyHop() {
        super("BunnyHop", "Continuously bunny hop for speed", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        boolean moving = mc.player.input.movementForward != 0 || mc.player.input.movementSideways != 0;
        if (!moving) return;

        if (mc.player.isOnGround()) {
            mc.player.jump();
            Vec3d vel = mc.player.getVelocity();
            mc.player.setVelocity(vel.x * boost.getValue(), vel.y, vel.z * boost.getValue());
        }
    }
}
