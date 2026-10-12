package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class HoleFiller extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Scan range for holes", 4.0, 1.0, 6.0, 0.5));

    public HoleFiller() {
        super("HoleFiller", "Fill nearby 1x1 holes with blocks", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        int blockSlot = findBlockSlot();
        if (blockSlot == -1) return;

        int r = (int) range.getValue();
        BlockPos center = mc.player.getBlockPos();

        for (BlockPos pos : BlockPos.iterateOutwards(center, r, r, r)) {
            if (!isHole(pos)) continue;

            // Found a 1x1 hole, place a block in it
            for (Direction dir : Direction.values()) {
                BlockPos neighbor = pos.offset(dir);
                if (mc.world.getBlockState(neighbor).isAir()) continue;

                int prevSlot = mc.player.getInventory().selectedSlot;
                mc.player.getInventory().selectedSlot = blockSlot;

                BlockHitResult hitResult = new BlockHitResult(
                        Vec3d.ofCenter(neighbor),
                        dir.getOpposite(),
                        neighbor,
                        false
                );
                mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hitResult);
                mc.player.swingHand(Hand.MAIN_HAND);

                mc.player.getInventory().selectedSlot = prevSlot;
                return;
            }
        }
    }

    private boolean isHole(BlockPos pos) {
        BlockState state = mc.world.getBlockState(pos);
        if (!state.isAir()) return false;

        // A 1x1 hole: air block surrounded by solid blocks on all four sides and below
        if (mc.world.getBlockState(pos.down()).isAir()) return false;

        int solidSides = 0;
        for (Direction dir : new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST}) {
            BlockState side = mc.world.getBlockState(pos.offset(dir));
            if (side.isOf(Blocks.OBSIDIAN) || state.isOf(Blocks.BEDROCK) || !side.isAir()) {
                solidSides++;
            }
        }
        return solidSides == 4;
    }

    private int findBlockSlot() {
        for (int i = 0; i < 9; i++) {
            if (mc.player.getInventory().getStack(i).getItem() instanceof BlockItem) {
                return i;
            }
        }
        return -1;
    }
}
