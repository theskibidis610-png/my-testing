package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.util.math.BlockPos;

public class AntiSuffocate extends Module {

    public AntiSuffocate() {
        super("AntiSuffocate", "Prevents suffocation by moving out of blocks", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        BlockPos headPos = BlockPos.ofFloored(mc.player.getEyePos());
        if (mc.world.getBlockState(headPos).isSolidBlock(mc.world, headPos)) {
            mc.player.setVelocity(0, 0.05, 0);
        }
    }
}
