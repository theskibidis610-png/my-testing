package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class TargetStrafe extends Module {
    private final NumberSetting radius = addSetting(new NumberSetting("Radius", "Strafe distance", 2.5, 0.5, 5.0, 0.5));
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Strafe speed", 0.3, 0.1, 1.0, 0.05));
    private boolean clockwise = true;

    public TargetStrafe() {
        super("TargetStrafe", "Circle strafe around target", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        PlayerEntity target = null;
        double closest = 6;
        for (PlayerEntity p : mc.world.getPlayers()) {
            if (p == mc.player) continue;
            double d = mc.player.distanceTo(p);
            if (d < closest) { closest = d; target = p; }
        }
        if (target == null) return;

        double angle = Math.atan2(mc.player.getZ() - target.getZ(), mc.player.getX() - target.getX());
        double dir = clockwise ? 1 : -1;
        angle += dir * speed.getValue();

        double newX = target.getX() + Math.cos(angle) * radius.getValue();
        double newZ = target.getZ() + Math.sin(angle) * radius.getValue();

        mc.player.setVelocity(
                (newX - mc.player.getX()) * 0.5,
                mc.player.getVelocity().y,
                (newZ - mc.player.getZ()) * 0.5
        );

        if (mc.player.horizontalCollision) clockwise = !clockwise;
    }
}
