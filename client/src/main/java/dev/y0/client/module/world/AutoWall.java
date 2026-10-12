package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class AutoWall extends Module {
    private final NumberSetting height = addSetting(new NumberSetting("Height", "Wall height", 3.0, 1.0, 6.0, 1.0));

    public AutoWall() {
        super("AutoWall", "Automatically build walls", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        Direction facing = mc.player.getHorizontalFacing();
        BlockPos base = mc.player.getBlockPos().offset(facing);

        for (int y = 0; y < (int) height.getValue(); y++) {
            BlockPos pos = base.up(y);
            if (mc.world.getBlockState(pos).isAir()) {
                BlockHitResult hit = new BlockHitResult(Vec3d.ofCenter(pos), Direction.UP, pos.down(), false);
                mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
                return;
            }
        }
    }
}
