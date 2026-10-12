package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class CaneBreaker extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Break range", 4.0, 1.0, 6.0, 0.5));

    public CaneBreaker() {
        super("CaneBreaker", "Auto break sugar cane above base", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        int r = (int) range.getValue();
        BlockPos center = mc.player.getBlockPos();

        for (BlockPos pos : BlockPos.iterateOutwards(center, r, r, r)) {
            if (mc.world.getBlockState(pos).isOf(Blocks.SUGAR_CANE)
                    && mc.world.getBlockState(pos.down()).isOf(Blocks.SUGAR_CANE)) {
                mc.interactionManager.attackBlock(pos, Direction.UP);
                return;
            }
        }
    }
}
