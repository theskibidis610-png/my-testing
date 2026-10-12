package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class PistonPush extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Piston placement range", 4.0, 1.0, 6.0, 1.0));

    public PistonPush() {
        super("PistonPush", "Auto push nearby entities using pistons", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        // Find piston in hotbar
        int pistonSlot = -1;
        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.player.getInventory().getStack(i);
            if (stack.getItem() instanceof BlockItem bi) {
                if (bi.getBlock() == Blocks.PISTON || bi.getBlock() == Blocks.STICKY_PISTON) {
                    pistonSlot = i;
                    break;
                }
            }
        }
        if (pistonSlot == -1) return;

        // Find a target position near entities
        BlockPos target = mc.player.getBlockPos().offset(mc.player.getHorizontalFacing(), 2);
        if (!mc.world.getBlockState(target).isAir()) return;

        int prevSlot = mc.player.getInventory().selectedSlot;
        mc.player.getInventory().selectedSlot = pistonSlot;

        BlockHitResult hit = new BlockHitResult(
                Vec3d.ofCenter(target), Direction.UP, target.down(), false
        );
        mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
        mc.player.getInventory().selectedSlot = prevSlot;
    }
}
