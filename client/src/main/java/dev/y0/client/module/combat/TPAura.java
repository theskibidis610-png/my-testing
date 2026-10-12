package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.Hand;

public class TPAura extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Maximum teleport range", 10.0, 1.0, 20.0, 1.0));

    public TPAura() {
        super("TPAura", "Teleports to target and attacks", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        Entity best = null;
        double bestDist = range.getValue();

        for (Entity entity : mc.world.getEntities()) {
            if (entity == mc.player) continue;
            if (!(entity instanceof PlayerEntity)) continue;
            if (entity instanceof LivingEntity living && (living.isDead() || living.getHealth() <= 0)) continue;

            double dist = mc.player.distanceTo(entity);
            if (dist < bestDist) {
                bestDist = dist;
                best = entity;
            }
        }

        if (best == null) return;

        double origX = mc.player.getX();
        double origY = mc.player.getY();
        double origZ = mc.player.getZ();

        mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(
                best.getX(), best.getY(), best.getZ(), true));

        mc.interactionManager.attackEntity(mc.player, best);
        mc.player.swingHand(Hand.MAIN_HAND);

        mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(
                origX, origY, origZ, true));
    }
}
