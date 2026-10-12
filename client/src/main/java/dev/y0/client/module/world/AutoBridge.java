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

public class AutoBridge extends Module {
    private final NumberSetting width = addSetting(new NumberSetting("Width", "Bridge width in blocks", 1.0, 1.0, 5.0, 1.0));

    public AutoBridge() {
        super("AutoBridge", "Automatically bridge forward", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        BlockPos below = mc.player.getBlockPos().down();
        int w = (int) width.getValue() / 2;

        for (int x = -w; x <= w; x++) {
            BlockPos pos = below.east(x);
            if (mc.world.getBlockState(pos).isAir()) {
                BlockHitResult hit = new BlockHitResult(Vec3d.ofCenter(pos), Direction.UP, pos.down(), false);
                mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
                return;
            }
        }
    }
}
