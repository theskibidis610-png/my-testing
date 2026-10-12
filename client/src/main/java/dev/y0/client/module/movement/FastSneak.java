package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

public class FastSneak extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Sneak speed multiplier", 2.0, 1.0, 5.0, 0.5));

    public FastSneak() {
        super("FastSneak", "Move faster while sneaking", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (mc.player.isSneaking()) {
            Vec3d vel = mc.player.getVelocity();
            mc.player.setVelocity(vel.x * speed.getValue(), vel.y, vel.z * speed.getValue());
        }
    }
}
