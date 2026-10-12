package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class PathMaker extends Module {
    private final NumberSetting width = addSetting(new NumberSetting("Width", "Path width", 1.0, 1.0, 5.0, 1.0));

    public PathMaker() {
        super("PathMaker", "Create grass paths as you walk", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;
        if (!mc.player.getMainHandStack().isOf(Items.WOODEN_SHOVEL)
                && !mc.player.getMainHandStack().isOf(Items.STONE_SHOVEL)
                && !mc.player.getMainHandStack().isOf(Items.IRON_SHOVEL)) return;

        int w = (int) width.getValue() / 2;
        BlockPos below = mc.player.getBlockPos().down();

        for (int x = -w; x <= w; x++) {
            BlockPos pos = below.east(x);
            if (mc.world.getBlockState(pos).isOf(Blocks.GRASS_BLOCK)) {
                BlockHitResult hit = new BlockHitResult(Vec3d.ofCenter(pos), Direction.UP, pos, false);
                mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
            }
        }
    }
}
