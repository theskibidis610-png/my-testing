package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class AutoTrap extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Target range", 4.0, 1.0, 6.0, 0.5));

    // Block positions relative to the target's feet for a full trap
    private static final BlockPos[] TRAP_OFFSETS = {
            new BlockPos(1, 0, 0),
            new BlockPos(-1, 0, 0),
            new BlockPos(0, 0, 1),
            new BlockPos(0, 0, -1),
            new BlockPos(1, 1, 0),
            new BlockPos(-1, 1, 0),
            new BlockPos(0, 1, 1),
            new BlockPos(0, 1, -1),
            new BlockPos(0, 2, 0)   // Top of head
    };

    public AutoTrap() {
        super("AutoTrap", "Trap nearby players with obsidian", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        int obsSlot = findObsidianSlot();
        if (obsSlot == -1) return;

        // Find nearest player
        PlayerEntity target = null;
        double closest = range.getValue();

        for (PlayerEntity player : mc.world.getPlayers()) {
            if (player == mc.player) continue;
            double dist = mc.player.distanceTo(player);
            if (dist < closest) {
                closest = dist;
                target = player;
            }
        }

        if (target == null) return;

        BlockPos targetFeet = target.getBlockPos();

        // Place one block per tick to avoid detection
        for (BlockPos offset : TRAP_OFFSETS) {
            BlockPos placePos = targetFeet.add(offset);
            if (!mc.world.getBlockState(placePos).isAir()) continue;

            // Find a solid neighbor to place against
            for (Direction dir : Direction.values()) {
                BlockPos neighbor = placePos.offset(dir);
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
                return;
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
