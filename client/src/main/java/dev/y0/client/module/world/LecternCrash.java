package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.block.Blocks;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class LecternCrash extends Module {

    public LecternCrash() {
        super("LecternCrash", "Lectern interaction exploit", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        BlockPos center = mc.player.getBlockPos();
        for (BlockPos pos : BlockPos.iterateOutwards(center, 4, 4, 4)) {
            if (mc.world.getBlockState(pos).isOf(Blocks.LECTERN)) {
                BlockHitResult hit = new BlockHitResult(Vec3d.ofCenter(pos), Direction.UP, pos, false);
                for (int i = 0; i < 100; i++) {
                    mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
                }
                return;
            }
        }
    }
}
