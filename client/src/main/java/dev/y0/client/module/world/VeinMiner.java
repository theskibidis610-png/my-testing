package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.block.Block;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.*;

public class VeinMiner extends Module {
    private final NumberSetting maxBlocks = addSetting(new NumberSetting("MaxBlocks", "Max blocks to mine in a vein", 16.0, 1.0, 64.0, 1.0));
    private final Queue<BlockPos> veinQueue = new LinkedList<>();

    public VeinMiner() {
        super("VeinMiner", "Mine connected ore blocks in a vein", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        if (veinQueue.isEmpty()) {
            // Search for nearest ore to start
            BlockPos center = mc.player.getBlockPos();
            for (BlockPos pos : BlockPos.iterateOutwards(center, 4, 4, 4)) {
                if (isOre(mc.world.getBlockState(pos).getBlock())) {
                    buildVein(pos, mc.world.getBlockState(pos).getBlock());
                    break;
                }
            }
        }

        if (!veinQueue.isEmpty()) {
            BlockPos pos = veinQueue.poll();
            if (!mc.world.getBlockState(pos).isAir()) {
                mc.interactionManager.attackBlock(pos, Direction.UP);
                mc.interactionManager.updateBlockBreakingProgress(pos, Direction.UP);
            }
        }
    }

    private void buildVein(BlockPos start, Block oreType) {
        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> search = new LinkedList<>();
        search.add(start);
        int max = (int) maxBlocks.getValue();

        while (!search.isEmpty() && visited.size() < max) {
            BlockPos pos = search.poll();
            if (visited.contains(pos)) continue;
            if (!mc.world.getBlockState(pos).isOf(oreType)) continue;
            visited.add(pos);
            veinQueue.add(pos);

            for (Direction dir : Direction.values()) {
                search.add(pos.offset(dir));
            }
        }
    }

    private boolean isOre(Block block) {
        var state = block.getDefaultState();
        return state.isIn(BlockTags.COAL_ORES) || state.isIn(BlockTags.IRON_ORES)
                || state.isIn(BlockTags.GOLD_ORES) || state.isIn(BlockTags.DIAMOND_ORES)
                || state.isIn(BlockTags.EMERALD_ORES) || state.isIn(BlockTags.LAPIS_ORES)
                || state.isIn(BlockTags.REDSTONE_ORES) || state.isIn(BlockTags.COPPER_ORES);
    }
}
