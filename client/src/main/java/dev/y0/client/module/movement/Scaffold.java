package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class Scaffold extends Module {
    private final BooleanSetting tower = addSetting(new BooleanSetting("Tower", "Place blocks upward while jumping", false));
    private final BooleanSetting autoSwitch = addSetting(new BooleanSetting("AutoSwitch", "Switch to blocks automatically", true));

    public Scaffold() {
        super("Scaffold", "Places blocks under you", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        BlockPos below = mc.player.getBlockPos().down();

        if (!mc.world.getBlockState(below).isAir()) return;

        int blockSlot = -1;
        if (autoSwitch.getValue()) {
            for (int i = 0; i < 9; i++) {
                if (mc.player.getInventory().getStack(i).getItem() instanceof BlockItem) {
                    blockSlot = i;
                    break;
                }
            }
            if (blockSlot == -1) return;
        } else {
            if (!(mc.player.getMainHandStack().getItem() instanceof BlockItem)) return;
        }

        int prevSlot = mc.player.getInventory().selectedSlot;
        if (blockSlot != -1) {
            mc.player.getInventory().selectedSlot = blockSlot;
        }

        for (Direction dir : Direction.values()) {
            BlockPos neighbor = below.offset(dir);
            if (!mc.world.getBlockState(neighbor).isAir()) {
                BlockHitResult hitResult = new BlockHitResult(
                        Vec3d.ofCenter(neighbor),
                        dir.getOpposite(),
                        neighbor,
                        false
                );
                mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hitResult);
                mc.player.swingHand(Hand.MAIN_HAND);
                break;
            }
        }

        if (blockSlot != -1) {
            mc.player.getInventory().selectedSlot = prevSlot;
        }
    }
}
