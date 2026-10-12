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

public class FloorPlacer extends Module {
    private final NumberSetting radius = addSetting(new NumberSetting("Radius", "Floor placement radius", 3.0, 1.0, 6.0, 0.5));

    public FloorPlacer() {
        super("FloorPlacer", "Automatically place floor blocks", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        int r = (int) radius.getValue();
        BlockPos below = mc.player.getBlockPos().down();

        for (BlockPos pos : BlockPos.iterateOutwards(below, r, 0, r)) {
            if (mc.world.getBlockState(pos).isAir() && !mc.world.getBlockState(pos.down()).isAir()) {
                BlockHitResult hit = new BlockHitResult(Vec3d.ofCenter(pos), Direction.UP, pos.down(), false);
                mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
                return;
            }
        }
    }
}
