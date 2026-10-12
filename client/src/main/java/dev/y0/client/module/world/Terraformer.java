package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class Terraformer extends Module {
    private final NumberSetting targetY = addSetting(new NumberSetting("TargetY", "Target Y level to flatten to", 64.0, -64.0, 320.0, 1.0));
    private final NumberSetting radius = addSetting(new NumberSetting("Radius", "Terraform radius", 4.0, 1.0, 8.0, 1.0));

    public Terraformer() {
        super("Terraformer", "Level terrain to a target Y level", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        int r = (int) radius.getValue();
        int ty = (int) targetY.getValue();
        BlockPos center = mc.player.getBlockPos();

        for (int x = -r; x <= r; x++) {
            for (int z = -r; z <= r; z++) {
                int bx = center.getX() + x;
                int bz = center.getZ() + z;

                // Break blocks above target Y
                for (int y = ty + 1; y <= ty + 10; y++) {
                    BlockPos pos = new BlockPos(bx, y, bz);
                    if (!mc.world.getBlockState(pos).isAir() && mc.world.getBlockState(pos).getHardness(mc.world, pos) >= 0) {
                        mc.interactionManager.attackBlock(pos, Direction.UP);
                        mc.interactionManager.updateBlockBreakingProgress(pos, Direction.UP);
                        return;
                    }
                }

                // Fill holes below target Y
                BlockPos fillPos = new BlockPos(bx, ty, bz);
                if (mc.world.getBlockState(fillPos).isAir() && mc.player.getMainHandStack().getItem() instanceof BlockItem) {
                    BlockHitResult hit = new BlockHitResult(Vec3d.ofCenter(fillPos), Direction.UP, fillPos.down(), false);
                    mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
                    return;
                }
            }
        }
    }
}
