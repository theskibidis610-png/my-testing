package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class MiningBot extends Module {
    private final NumberSetting depth = addSetting(new NumberSetting("Depth", "Mining depth target", 12.0, 1.0, 64.0, 1.0));
    private int currentDepth;

    public MiningBot() {
        super("MiningBot", "Automated mining straight down", Category.WORLD);
    }

    @Override
    protected void onEnable() {
        currentDepth = 0;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;
        if (currentDepth >= (int) depth.getValue()) return;

        BlockPos below = mc.player.getBlockPos().down();
        if (!mc.world.getBlockState(below).isAir() && mc.world.getBlockState(below).getHardness(mc.world, below) >= 0) {
            mc.interactionManager.attackBlock(below, Direction.UP);
            mc.interactionManager.updateBlockBreakingProgress(below, Direction.UP);
        } else {
            currentDepth++;
        }
    }
}
