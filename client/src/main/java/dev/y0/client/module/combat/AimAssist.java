package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

public class AimAssist extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Rotation speed", 5.0, 1.0, 20.0, 0.5));
    private final NumberSetting fov = addSetting(new NumberSetting("FOV", "Field of view", 90.0, 10.0, 180.0, 5.0));
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Target range", 4.5, 1.0, 8.0, 0.5));
    private final BooleanSetting playersOnly = addSetting(new BooleanSetting("Players Only", "Only aim at players", true));

    public AimAssist() {
        super("AimAssist", "Smoothly aims at targets", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        Entity target = findTarget();
        if (target == null) return;

        double dx = target.getX() - mc.player.getX();
        double dy = (target.getEyeY() - mc.player.getEyeY());
        double dz = target.getZ() - mc.player.getZ();
        double dist = Math.sqrt(dx * dx + dz * dz);

        float targetYaw = (float) Math.toDegrees(Math.atan2(dz, dx)) - 90f;
        float targetPitch = (float) -Math.toDegrees(Math.atan2(dy, dist));
        float spd = (float) speed.getValue();

        mc.player.setYaw(mc.player.getYaw() + MathHelper.wrapDegrees(targetYaw - mc.player.getYaw()) / (20f / spd));
        mc.player.setPitch(mc.player.getPitch() + MathHelper.wrapDegrees(targetPitch - mc.player.getPitch()) / (20f / spd));
    }

    private Entity findTarget() {
        Entity best = null;
        double bestDist = range.getValue();
        for (Entity e : mc.world.getEntities()) {
            if (e == mc.player || !(e instanceof LivingEntity l) || l.isDead()) continue;
            if (playersOnly.getValue() && !(e instanceof PlayerEntity)) continue;
            double d = mc.player.distanceTo(e);
            if (d < bestDist) { bestDist = d; best = e; }
        }
        return best;
    }
}
