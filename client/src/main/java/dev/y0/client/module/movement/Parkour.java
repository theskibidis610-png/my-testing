package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.util.math.BlockPos;

public class Parkour extends Module {

    public Parkour() {
        super("Parkour", "Auto jump at block edges", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        if (!mc.player.isOnGround()) return;

        BlockPos below = new BlockPos(
                (int) Math.floor(mc.player.getX()),
                (int) Math.floor(mc.player.getY()) - 1,
                (int) Math.floor(mc.player.getZ())
        );

        boolean moving = mc.player.input.movementForward != 0 || mc.player.input.movementSideways != 0;
        if (moving && mc.world.getBlockState(below).isAir()) {
            mc.player.jump();
        }
    }
}
