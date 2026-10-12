package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class AutoFarm extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Farm scan range", 3.0, 1.0, 6.0, 0.5));

    public AutoFarm() {
        super("AutoFarm", "Automatically harvests and replants crops", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        int r = (int) range.getValue();
        BlockPos center = mc.player.getBlockPos();

        for (BlockPos pos : BlockPos.iterateOutwards(center, r, r, r)) {
            BlockState state = mc.world.getBlockState(pos);

            // Check for fully grown crops
            if (state.getBlock() instanceof CropBlock crop) {
                if (crop.isMature(state)) {
                    // Break the mature crop
                    mc.interactionManager.attackBlock(pos, Direction.UP);
                    mc.interactionManager.updateBlockBreakingProgress(pos, Direction.UP);
                    return;
                }
            }

            // Check for empty farmland and replant
            if (state.isOf(Blocks.FARMLAND)) {
                BlockState above = mc.world.getBlockState(pos.up());
                if (above.isAir()) {
                    // Find seeds in hotbar
                    int seedSlot = findSeedSlot();
                    if (seedSlot == -1) continue;

                    int prevSlot = mc.player.getInventory().selectedSlot;
                    mc.player.getInventory().selectedSlot = seedSlot;

                    BlockHitResult hitResult = new BlockHitResult(
                            Vec3d.ofCenter(pos),
                            Direction.UP,
                            pos,
                            false
                    );
                    mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hitResult);

                    mc.player.getInventory().selectedSlot = prevSlot;
                    return;
                }
            }
        }
    }

    private int findSeedSlot() {
        for (int i = 0; i < 9; i++) {
            var item = mc.player.getInventory().getStack(i).getItem();
            if (item == Items.WHEAT_SEEDS || item == Items.CARROT
                    || item == Items.POTATO || item == Items.BEETROOT_SEEDS) {
                return i;
            }
        }
        return -1;
    }
}
