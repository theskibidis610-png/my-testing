package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;

public class Slime extends Module {

    public Slime() {
        super("Slime", "No slime block bounce", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        BlockPos below = mc.player.getBlockPos().down();
        if (mc.world.getBlockState(below).getBlock() == Blocks.SLIME_BLOCK) {
            if (mc.player.getVelocity().y < 0) {
                mc.player.setVelocity(mc.player.getVelocity().x, 0, mc.player.getVelocity().z);
            }
        }
    }
}
