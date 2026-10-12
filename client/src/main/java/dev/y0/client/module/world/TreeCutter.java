package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class TreeCutter extends Module {
    private final NumberSetting maxHeight = addSetting(new NumberSetting("MaxHeight", "Maximum tree height to cut", 16.0, 3.0, 30.0, 1.0));

    public TreeCutter() {
        super("TreeCutter", "Cut down entire trees from bottom to top", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        int maxH = (int) maxHeight.getValue();
        BlockPos center = mc.player.getBlockPos();

        // Search nearby for log blocks
        for (BlockPos pos : BlockPos.iterateOutwards(center, 4, 1, 4)) {
            if (!mc.world.getBlockState(pos).isIn(BlockTags.LOGS)) continue;

            // Found a log, break upward
            for (int y = 0; y < maxH; y++) {
                BlockPos logPos = pos.up(y);
                if (!mc.world.getBlockState(logPos).isIn(BlockTags.LOGS)) break;

                mc.interactionManager.attackBlock(logPos, Direction.UP);
                mc.interactionManager.updateBlockBreakingProgress(logPos, Direction.UP);
                return;
            }
        }
    }
}
