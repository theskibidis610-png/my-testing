package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.block.BedBlock;
import net.minecraft.item.BedItem;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class BedBomb extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Placement range", 4.0, 1.0, 6.0, 0.5));

    public BedBomb() {
        super("BedBomb", "Place and click beds for explosions in Nether/End", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        // Only works in Nether and End
        if (mc.world.getDimension().bedWorks()) return;

        int r = (int) range.getValue();
        BlockPos center = mc.player.getBlockPos();

        // Click existing beds to explode
        for (BlockPos pos : BlockPos.iterateOutwards(center, r, r, r)) {
            if (mc.world.getBlockState(pos).getBlock() instanceof BedBlock) {
                BlockHitResult hit = new BlockHitResult(Vec3d.ofCenter(pos), Direction.UP, pos, false);
                mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
                return;
            }
        }

        // Place a bed if holding one
        if (mc.player.getMainHandStack().getItem() instanceof BedItem) {
            BlockPos target = center.offset(mc.player.getHorizontalFacing());
            if (mc.world.getBlockState(target).isAir()) {
                BlockHitResult hit = new BlockHitResult(Vec3d.ofCenter(target), Direction.UP, target.down(), false);
                mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
            }
        }
    }
}
