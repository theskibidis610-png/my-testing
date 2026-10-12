package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;

public class Jesus extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Water walk method", "Solid", "Solid", "Dolphin"));

    public Jesus() {
        super("Jesus", "Walk on water", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        if (mc.player.isTouchingWater() || mc.player.isSubmergedInWater()) {
            switch (mode.getValue()) {
                case "Solid" -> {
                    mc.player.setVelocity(mc.player.getVelocity().x, 0.11, mc.player.getVelocity().z);
                    if (isOnWater()) {
                        mc.player.setVelocity(mc.player.getVelocity().x, 0.0, mc.player.getVelocity().z);
                        mc.player.setOnGround(true);
                    }
                }
                case "Dolphin" -> {
                    mc.player.setVelocity(
                            mc.player.getVelocity().x * 1.04,
                            mc.player.getVelocity().y + 0.03,
                            mc.player.getVelocity().z * 1.04
                    );
                }
            }
        }
    }

    private boolean isOnWater() {
        BlockPos below = mc.player.getBlockPos().down();
        var state = mc.world.getBlockState(below);
        return state.isOf(Blocks.WATER) || state.isOf(Blocks.LAVA);
    }
}
