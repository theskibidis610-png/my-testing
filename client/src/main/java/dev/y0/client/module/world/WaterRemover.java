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

public class WaterRemover extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Removal range", 4.0, 1.0, 8.0, 0.5));

    public WaterRemover() {
        super("WaterRemover", "Remove water source blocks", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        int bucketSlot = -1;
        for (int i = 0; i < 9; i++) {
            if (mc.player.getInventory().getStack(i).isOf(Items.BUCKET)) { bucketSlot = i; break; }
        }
        if (bucketSlot == -1) return;

        int r = (int) range.getValue();
        BlockPos center = mc.player.getBlockPos();

        for (BlockPos pos : BlockPos.iterateOutwards(center, r, r, r)) {
            if (mc.world.getBlockState(pos).isOf(Blocks.WATER)) {
                int prev = mc.player.getInventory().selectedSlot;
                mc.player.getInventory().selectedSlot = bucketSlot;
                BlockHitResult hit = new BlockHitResult(Vec3d.ofCenter(pos), Direction.UP, pos, false);
                mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
                mc.player.getInventory().selectedSlot = prev;
                return;
            }
        }
    }
}
