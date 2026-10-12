package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class AutoCrystal extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Crystal range", 4.5, 1.0, 6.0, 0.1));
    private final NumberSetting breakRange = addSetting(new NumberSetting("Break Range", "Crystal break range", 4.5, 1.0, 6.0, 0.1));
    private final BooleanSetting autoBreak = addSetting(new BooleanSetting("Break", "Auto break crystals", true));
    private final BooleanSetting autoPlace = addSetting(new BooleanSetting("Place", "Auto place crystals", true));
    private final BooleanSetting antiSuicide = addSetting(new BooleanSetting("Anti Suicide", "Don't kill yourself", true));

    public AutoCrystal() {
        super("AutoCrystal", "Automatic crystal PvP", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        if (autoBreak.getValue()) {
            breakCrystals();
        }

        if (autoPlace.getValue()) {
            placeCrystals();
        }
    }

    private void breakCrystals() {
        for (Entity entity : mc.world.getEntities()) {
            if (!(entity instanceof EndCrystalEntity)) continue;
            if (mc.player.distanceTo(entity) > breakRange.getValue()) continue;

            if (antiSuicide.getValue()) {
                float damage = calculateDamage(entity.getPos(), mc.player);
                if (damage >= mc.player.getHealth()) continue;
            }

            mc.interactionManager.attackEntity(mc.player, entity);
            mc.player.swingHand(Hand.MAIN_HAND);
            break;
        }
    }

    private void placeCrystals() {
        if (mc.player.getMainHandStack().getItem() != Items.END_CRYSTAL
                && mc.player.getOffHandStack().getItem() != Items.END_CRYSTAL) return;

        PlayerEntity target = findTarget();
        if (target == null) return;

        BlockPos bestPos = null;
        double bestDamage = 0;

        for (BlockPos pos : BlockPos.iterateOutwards(mc.player.getBlockPos(), 5, 3, 5)) {
            if (!canPlaceCrystal(pos)) continue;
            if (mc.player.squaredDistanceTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5) > range.getValue() * range.getValue()) continue;

            float damage = calculateDamage(Vec3d.ofCenter(pos).add(0, 1, 0), target);
            if (antiSuicide.getValue()) {
                float selfDamage = calculateDamage(Vec3d.ofCenter(pos).add(0, 1, 0), mc.player);
                if (selfDamage >= mc.player.getHealth()) continue;
            }

            if (damage > bestDamage) {
                bestDamage = damage;
                bestPos = pos;
            }
        }

        if (bestPos != null) {
            Hand hand = mc.player.getMainHandStack().getItem() == Items.END_CRYSTAL ? Hand.MAIN_HAND : Hand.OFF_HAND;
            BlockHitResult hitResult = new BlockHitResult(Vec3d.ofCenter(bestPos), Direction.UP, bestPos, false);
            mc.interactionManager.interactBlock(mc.player, hand, hitResult);
        }
    }

    private PlayerEntity findTarget() {
        PlayerEntity closest = null;
        double closestDist = Double.MAX_VALUE;

        for (PlayerEntity player : mc.world.getPlayers()) {
            if (player == mc.player) continue;
            double dist = mc.player.distanceTo(player);
            if (dist < closestDist && dist <= range.getValue() + 3) {
                closestDist = dist;
                closest = player;
            }
        }
        return closest;
    }

    private boolean canPlaceCrystal(BlockPos pos) {
        var state = mc.world.getBlockState(pos);
        return (state.isOf(net.minecraft.block.Blocks.OBSIDIAN) || state.isOf(net.minecraft.block.Blocks.BEDROCK))
                && mc.world.getBlockState(pos.up()).isAir()
                && mc.world.getBlockState(pos.up(2)).isAir();
    }

    private float calculateDamage(Vec3d crystalPos, PlayerEntity target) {
        double dist = target.squaredDistanceTo(crystalPos);
        if (dist > 144) return 0;
        double exposure = 1.0 - Math.sqrt(dist) / 12.0;
        return (float) (exposure * 12.0);
    }
}
