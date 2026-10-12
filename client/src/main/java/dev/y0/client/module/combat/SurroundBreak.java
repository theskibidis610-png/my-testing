package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class SurroundBreak extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Break range", 4.0, 1.0, 6.0, 0.5));

    public SurroundBreak() {
        super("SurroundBreak", "Break surrounding obsidian", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;
        for (PlayerEntity p : mc.world.getPlayers()) {
            if (p == mc.player || mc.player.distanceTo(p) > range.getValue()) continue;
            BlockPos base = p.getBlockPos();
            for (Direction dir : Direction.Type.HORIZONTAL) {
                BlockPos pos = base.offset(dir);
                if (mc.world.getBlockState(pos).getBlock() == Blocks.OBSIDIAN) {
                    mc.interactionManager.attackBlock(pos, dir.getOpposite());
                    return;
                }
            }
        }
    }
}
