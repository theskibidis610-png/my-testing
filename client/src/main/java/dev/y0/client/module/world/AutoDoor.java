package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.block.DoorBlock;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class AutoDoor extends Module {
    private final BooleanSetting autoClose = addSetting(new BooleanSetting("AutoClose", "Close doors behind you", true));

    public AutoDoor() {
        super("AutoDoor", "Automatically open and close doors", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        BlockPos center = mc.player.getBlockPos();
        for (BlockPos pos : BlockPos.iterateOutwards(center, 2, 2, 2)) {
            if (mc.world.getBlockState(pos).getBlock() instanceof DoorBlock) {
                boolean open = mc.world.getBlockState(pos).get(DoorBlock.OPEN);
                double dist = mc.player.squaredDistanceTo(Vec3d.ofCenter(pos));
                if ((!open && dist < 4) || (autoClose.getValue() && open && dist > 9)) {
                    BlockHitResult hit = new BlockHitResult(Vec3d.ofCenter(pos), Direction.UP, pos, false);
                    mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
                }
            }
        }
    }
}
