package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.util.math.BlockPos;

public class EdgeJump extends Module {
    private final BooleanSetting auto = addSetting(new BooleanSetting("Auto", "Automatically jump at edges", true));

    public EdgeJump() {
        super("EdgeJump", "Jump when reaching block edges", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        if (!mc.player.isOnGround() || !auto.getValue()) return;

        boolean moving = mc.player.input.movementForward != 0 || mc.player.input.movementSideways != 0;
        if (!moving) return;

        BlockPos below = mc.player.getBlockPos().down();
        BlockPos ahead = below.offset(mc.player.getHorizontalFacing());
        if (mc.world.getBlockState(ahead).isAir()) {
            mc.player.jump();
        }
    }
}
