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

public class Replanter extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Replant scan range", 4.0, 1.0, 8.0, 0.5));

    public Replanter() {
        super("Replanter", "Auto replant saplings on dirt/grass", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        int saplingSlot = -1;
        for (int i = 0; i < 9; i++) {
            if (mc.player.getInventory().getStack(i).isOf(Items.OAK_SAPLING)) { saplingSlot = i; break; }
        }
        if (saplingSlot == -1) return;

        int r = (int) range.getValue();
        BlockPos center = mc.player.getBlockPos();

        for (BlockPos pos : BlockPos.iterateOutwards(center, r, r, r)) {
            if (mc.world.getBlockState(pos).isAir()
                    && (mc.world.getBlockState(pos.down()).isOf(Blocks.GRASS_BLOCK)
                    || mc.world.getBlockState(pos.down()).isOf(Blocks.DIRT))) {
                int prev = mc.player.getInventory().selectedSlot;
                mc.player.getInventory().selectedSlot = saplingSlot;
                BlockHitResult hit = new BlockHitResult(Vec3d.ofCenter(pos), Direction.UP, pos.down(), false);
                mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
                mc.player.getInventory().selectedSlot = prev;
                return;
            }
        }
    }
}
