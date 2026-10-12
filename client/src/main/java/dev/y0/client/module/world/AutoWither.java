package dev.y0.client.module.world;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class AutoWither extends Module {
    public AutoWither() {
        super("AutoWither", "Automatically build a wither structure on enable", Category.WORLD);
    }

    @Override
    protected void onEnable() {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) {
            disable();
            return;
        }

        BlockPos base = mc.player.getBlockPos().offset(Direction.NORTH, 2);

        // Wither pattern: soul sand T-shape + 3 skulls
        // Base column
        placeBlock(base);
        placeBlock(base.up());
        // Arms
        placeBlock(base.up().east());
        placeBlock(base.up().west());

        // Skulls would require placing wither skeleton skulls on top
        // This is a simplified structure builder

        disable();
    }

    private void placeBlock(BlockPos pos) {
        if (mc.world.getBlockState(pos).isAir()) {
            BlockHitResult hit = new BlockHitResult(
                    Vec3d.ofCenter(pos), Direction.UP, pos.down(), false
            );
            mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
        }
    }
}
