package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

public class MoonGravity extends Module {
    private final NumberSetting gravity = addSetting(new NumberSetting("Gravity", "Gravity multiplier (lower = floatier)", 0.3, 0.05, 1.0, 0.05));

    public MoonGravity() {
        super("MoonGravity", "Reduce gravity for floaty movement", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.player.isOnGround()) return;
        Vec3d vel = mc.player.getVelocity();
        double normalGravity = -0.0784;
        double adjustment = normalGravity * (1.0 - gravity.getValue());
        mc.player.setVelocity(vel.x, vel.y - adjustment, vel.z);
    }
}
