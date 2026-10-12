package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class Surround extends Module {
    private final BooleanSetting center = addSetting(new BooleanSetting("Center", "Center player on block before placing", true));

    // Cardinal offsets around the player's feet
    private static final BlockPos[] OFFSETS = {
            new BlockPos(1, 0, 0),
            new BlockPos(-1, 0, 0),
            new BlockPos(0, 0, 1),
            new BlockPos(0, 0, -1)
    };

    public Surround() {
        super("Surround", "Place obsidian around your feet", Category.WORLD);
    }

    @Override
    protected void onEnable() {
        if (mc.player != null && center.getValue()) {
            // Snap player to block center
            double x = Math.floor(mc.player.getX()) + 0.5;
            double z = Math.floor(mc.player.getZ()) + 0.5;
            mc.player.setPosition(x, mc.player.getY(), z);
        }
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        // Find obsidian in hotbar
        int obsSlot = findObsidianSlot();
        if (obsSlot == -1) return;

        BlockPos feet = mc.player.getBlockPos();

        for (BlockPos offset : OFFSETS) {
            BlockPos target = feet.add(offset);
            if (!mc.world.getBlockState(target).isAir()) continue;

            // Find a solid neighbor to place against
            for (Direction dir : Direction.values()) {
                BlockPos neighbor = target.offset(dir);
                if (mc.world.getBlockState(neighbor).isAir()) continue;

                int prevSlot = mc.player.getInventory().selectedSlot;
                mc.player.getInventory().selectedSlot = obsSlot;

                BlockHitResult hitResult = new BlockHitResult(
                        Vec3d.ofCenter(neighbor),
                        dir.getOpposite(),
                        neighbor,
                        false
                );
                mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hitResult);
                mc.player.swingHand(Hand.MAIN_HAND);

                mc.player.getInventory().selectedSlot = prevSlot;
                break;
            }
        }
    }

    private int findObsidianSlot() {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.player.getInventory().getStack(i);
            if (stack.getItem() instanceof BlockItem blockItem) {
                if (blockItem.getBlock() == Blocks.OBSIDIAN) {
                    return i;
                }
            }
        }
        return -1;
    }
}
