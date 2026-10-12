package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class AutoStaircase extends Module {
    private final ModeSetting direction = addSetting(new ModeSetting("Direction", "Staircase direction", "Up", "Up", "Down", "Spiral"));

    public AutoStaircase() {
        super("AutoStaircase", "Build staircases automatically", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        BlockPos target = switch (direction.getValue()) {
            case "Down" -> mc.player.getBlockPos().down(2);
            case "Spiral" -> mc.player.getBlockPos().down().east();
            default -> mc.player.getBlockPos().up(2);
        };

        if (mc.world.getBlockState(target).isAir() && direction.getValue().equals("Up")) {
            BlockHitResult hit = new BlockHitResult(Vec3d.ofCenter(target), Direction.UP, target.down(), false);
            mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
        } else if (!mc.world.getBlockState(target).isAir() && !direction.getValue().equals("Up")) {
            mc.interactionManager.attackBlock(target, Direction.UP);
        }
    }
}
