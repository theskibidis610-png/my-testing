package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class IceSpeed extends Module {
    private final NumberSetting multiplier = addSetting(new NumberSetting("Multiplier", "Speed multiplier on ice", 2.0, 1.0, 5.0, 0.5));

    public IceSpeed() {
        super("IceSpeed", "Move faster on ice", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        BlockPos below = mc.player.getBlockPos().down();
        var block = mc.world.getBlockState(below).getBlock();

        if (block == Blocks.ICE || block == Blocks.PACKED_ICE || block == Blocks.BLUE_ICE || block == Blocks.FROSTED_ICE) {
            Vec3d vel = mc.player.getVelocity();
            mc.player.setVelocity(vel.x * multiplier.getValue(), vel.y, vel.z * multiplier.getValue());
        }
    }
}
