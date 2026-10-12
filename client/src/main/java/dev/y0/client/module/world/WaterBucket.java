package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class WaterBucket extends Module {
    private final NumberSetting heightThreshold = addSetting(new NumberSetting("Height", "Fall height to trigger MLG", 5.0, 3.0, 20.0, 1.0));

    public WaterBucket() {
        super("WaterBucket", "Auto water bucket MLG when falling", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;
        if (mc.player.isOnGround() || mc.player.isTouchingWater()) return;
        if (mc.player.getVelocity().y >= 0) return;

        // Check fall distance
        if (mc.player.fallDistance < heightThreshold.getValue()) return;

        // Find water bucket in hotbar
        int bucketSlot = -1;
        for (int i = 0; i < 9; i++) {
            if (mc.player.getInventory().getStack(i).isOf(Items.WATER_BUCKET)) {
                bucketSlot = i;
                break;
            }
        }
        if (bucketSlot == -1) return;

        // Look down and place water
        BlockPos below = mc.player.getBlockPos().down();
        while (mc.world.getBlockState(below).isAir() && below.getY() > mc.world.getBottomY()) {
            below = below.down();
        }

        int prevSlot = mc.player.getInventory().selectedSlot;
        mc.player.getInventory().selectedSlot = bucketSlot;

        BlockHitResult hit = new BlockHitResult(Vec3d.ofCenter(below.up()), Direction.UP, below, false);
        mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);

        mc.player.getInventory().selectedSlot = prevSlot;
    }
}
