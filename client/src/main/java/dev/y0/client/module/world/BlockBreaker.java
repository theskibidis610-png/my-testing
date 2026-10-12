package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class BlockBreaker extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Break range", 4.0, 1.0, 6.0, 0.5));
    private final ModeSetting blockType = addSetting(new ModeSetting("Block", "Block type to target", "Torch",
            "Torch", "Bed", "Cake", "TallGrass", "Web"));

    public BlockBreaker() {
        super("BlockBreaker", "Break specific blocks around you", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        int r = (int) range.getValue();
        BlockPos center = mc.player.getBlockPos();

        for (BlockPos pos : BlockPos.iterateOutwards(center, r, r, r)) {
            BlockState state = mc.world.getBlockState(pos);
            if (state.isAir()) continue;

            if (isTargetBlock(state)) {
                mc.interactionManager.attackBlock(pos, Direction.UP);
                mc.interactionManager.updateBlockBreakingProgress(pos, Direction.UP);
                return;
            }
        }
    }

    private boolean isTargetBlock(BlockState state) {
        return switch (blockType.getValue()) {
            case "Torch" -> state.isOf(Blocks.TORCH) || state.isOf(Blocks.WALL_TORCH)
                    || state.isOf(Blocks.SOUL_TORCH) || state.isOf(Blocks.SOUL_WALL_TORCH);
            case "Bed" -> state.isOf(Blocks.RED_BED) || state.isOf(Blocks.WHITE_BED)
                    || state.isOf(Blocks.BLUE_BED) || state.isOf(Blocks.GREEN_BED);
            case "Cake" -> state.isOf(Blocks.CAKE);
            case "TallGrass" -> state.isOf(Blocks.SHORT_GRASS) || state.isOf(Blocks.TALL_GRASS);
            case "Web" -> state.isOf(Blocks.COBWEB);
            default -> false;
        };
    }
}
