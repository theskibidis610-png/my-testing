package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class Nuker extends Module {
    private final NumberSetting radius = addSetting(new NumberSetting("Radius", "Break radius", 3.0, 1.0, 6.0, 0.5));
    private final BooleanSetting flatMode = addSetting(new BooleanSetting("Flat", "Only break blocks at your Y level", false));

    public Nuker() {
        super("Nuker", "Break blocks around you", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        int r = (int) radius.getValue();
        BlockPos center = mc.player.getBlockPos();

        for (BlockPos pos : BlockPos.iterateOutwards(center, r, flatMode.getValue() ? 0 : r, r)) {
            BlockState state = mc.world.getBlockState(pos);
            if (state.isAir()) continue;
            if (state.getHardness(mc.world, pos) < 0) continue;

            mc.interactionManager.attackBlock(pos, Direction.UP);
            mc.interactionManager.updateBlockBreakingProgress(pos, Direction.UP);
            break;
        }
    }
}
