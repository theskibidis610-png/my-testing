package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class Quarry extends Module {
    private final NumberSetting size = addSetting(new NumberSetting("Size", "Quarry size (NxN)", 4.0, 2.0, 16.0, 1.0));
    private int currentY;
    private int startX, startZ;

    public Quarry() {
        super("Quarry", "Auto mine in a quarry pattern layer by layer", Category.WORLD);
    }

    @Override
    protected void onEnable() {
        if (mc.player != null) {
            BlockPos pos = mc.player.getBlockPos();
            currentY = pos.getY() - 1;
            startX = pos.getX();
            startZ = pos.getZ();
        }
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;
        if (currentY < mc.world.getBottomY()) return;

        int s = (int) size.getValue();

        for (int x = startX; x < startX + s; x++) {
            for (int z = startZ; z < startZ + s; z++) {
                BlockPos pos = new BlockPos(x, currentY, z);
                if (mc.world.getBlockState(pos).isAir()) continue;
                if (mc.world.getBlockState(pos).getHardness(mc.world, pos) < 0) continue;

                mc.interactionManager.attackBlock(pos, Direction.UP);
                mc.interactionManager.updateBlockBreakingProgress(pos, Direction.UP);
                return;
            }
        }
        currentY--;
    }
}
