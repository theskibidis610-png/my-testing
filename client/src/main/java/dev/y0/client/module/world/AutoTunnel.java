package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class AutoTunnel extends Module {
    private final NumberSetting height = addSetting(new NumberSetting("Height", "Tunnel height in blocks", 3.0, 2.0, 4.0, 1.0));

    public AutoTunnel() {
        super("AutoTunnel", "Automatically dig tunnels forward", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        Direction facing = mc.player.getHorizontalFacing();
        BlockPos base = mc.player.getBlockPos().offset(facing);

        for (int y = 0; y < (int) height.getValue(); y++) {
            BlockPos pos = base.up(y);
            if (!mc.world.getBlockState(pos).isAir()) {
                mc.interactionManager.attackBlock(pos, facing.getOpposite());
                mc.interactionManager.updateBlockBreakingProgress(pos, facing.getOpposite());
                return;
            }
        }
    }
}
