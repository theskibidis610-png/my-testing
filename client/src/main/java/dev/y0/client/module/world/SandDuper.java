package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

/**
 * Attempts the sand duplication exploit by breaking sand at the
 * right tick when it becomes a falling entity.
 */
public class SandDuper extends Module {

    public SandDuper() {
        super("SandDuper", "Sand duplication exploit", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        BlockPos center = mc.player.getBlockPos();
        for (BlockPos pos : BlockPos.iterateOutwards(center, 3, 3, 3)) {
            if (mc.world.getBlockState(pos).isOf(Blocks.SAND)
                    && mc.world.getBlockState(pos.down()).isAir()) {
                mc.interactionManager.attackBlock(pos, Direction.UP);
                return;
            }
        }
    }
}
