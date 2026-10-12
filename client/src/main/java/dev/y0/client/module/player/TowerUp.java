package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class TowerUp extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Tower speed multiplier", 1.0, 0.5, 2.0, 0.1));

    public TowerUp() {
        super("TowerUp", "Automatically towers straight up", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.interactionManager == null) return;
        if (!mc.options.jumpKey.isPressed()) return;

        ItemStack held = mc.player.getMainHandStack();
        if (!(held.getItem() instanceof BlockItem)) return;

        BlockPos below = mc.player.getBlockPos().down();
        if (!mc.world.getBlockState(below).isReplaceable()) {
            mc.player.jump();
            mc.player.setVelocity(mc.player.getVelocity().multiply(1.0, speed.getValue(), 1.0));
            return;
        }

        BlockHitResult hitResult = new BlockHitResult(
                Vec3d.ofCenter(below), Direction.UP, below, false
        );

        mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hitResult);
        mc.player.swingHand(Hand.MAIN_HAND);
        mc.player.jump();
    }
}
