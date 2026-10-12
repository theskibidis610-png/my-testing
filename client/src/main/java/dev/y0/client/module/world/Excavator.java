package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class Excavator extends Module {
    private final NumberSetting radius = addSetting(new NumberSetting("Radius", "Excavation radius", 3.0, 1.0, 8.0, 0.5));

    public Excavator() {
        super("Excavator", "Excavate large areas down to bedrock", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        int r = (int) radius.getValue();
        BlockPos center = mc.player.getBlockPos().down();

        for (BlockPos pos : BlockPos.iterateOutwards(center, r, 0, r)) {
            if (!mc.world.getBlockState(pos).isAir() && mc.world.getBlockState(pos).getHardness(mc.world, pos) >= 0) {
                mc.interactionManager.attackBlock(pos, Direction.UP);
                mc.interactionManager.updateBlockBreakingProgress(pos, Direction.UP);
                return;
            }
        }
    }
}
