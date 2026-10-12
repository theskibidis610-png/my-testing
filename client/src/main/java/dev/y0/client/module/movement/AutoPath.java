package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

public class AutoPath extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Pathfinding walk speed", 0.3, 0.1, 1.0, 0.05));
    private double targetX, targetY, targetZ;

    public AutoPath() {
        super("AutoPath", "Auto pathfind toward coordinates", Category.MOVEMENT);
    }

    @Override
    protected void onEnable() {
        if (mc.player != null) {
            Vec3d look = mc.player.getRotationVector();
            targetX = mc.player.getX() + look.x * 50;
            targetY = mc.player.getY();
            targetZ = mc.player.getZ() + look.z * 50;
        }
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        double dx = targetX - mc.player.getX();
        double dz = targetZ - mc.player.getZ();
        double dist = Math.sqrt(dx * dx + dz * dz);
        if (dist < 1.0) return;
        mc.player.setVelocity(dx / dist * speed.getValue(), mc.player.getVelocity().y, dz / dist * speed.getValue());
    }
}
