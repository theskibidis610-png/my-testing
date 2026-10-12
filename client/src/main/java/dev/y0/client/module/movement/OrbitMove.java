package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class OrbitMove extends Module {
    private final NumberSetting radius = addSetting(new NumberSetting("Radius", "Orbit radius", 3.0, 1.0, 6.0, 0.5));
    private double angle;

    public OrbitMove() {
        super("OrbitMove", "Orbit around nearest entity", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        LivingEntity target = null;
        double closest = 10.0;
        for (Entity entity : mc.world.getEntities()) {
            if (entity instanceof LivingEntity living && entity != mc.player) {
                double dist = mc.player.distanceTo(entity);
                if (dist < closest) {
                    closest = dist;
                    target = living;
                }
            }
        }

        if (target == null) return;

        angle += 5;
        double rad = Math.toRadians(angle);
        double r = radius.getValue();
        double x = target.getX() + Math.cos(rad) * r;
        double z = target.getZ() + Math.sin(rad) * r;

        mc.player.setPosition(x, mc.player.getY(), z);
    }
}
