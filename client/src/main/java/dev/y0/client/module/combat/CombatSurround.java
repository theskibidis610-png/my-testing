package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class CombatSurround extends Module {
    private final BooleanSetting autoEnable = addSetting(new BooleanSetting("Auto Enable", "Auto enable when in danger", false));

    private static final Direction[] HORIZONTAL = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};

    public CombatSurround() {
        super("CombatSurround", "Places obsidian around self for protection", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        int obsSlot = -1;
        for (int i = 0; i < 9; i++) {
            if (mc.player.getInventory().getStack(i).getItem() == Items.OBSIDIAN) {
                obsSlot = i;
                break;
            }
        }
        if (obsSlot == -1) return;

        BlockPos playerPos = mc.player.getBlockPos();
        int oldSlot = mc.player.getInventory().selectedSlot;
        mc.player.getInventory().selectedSlot = obsSlot;

        for (Direction dir : HORIZONTAL) {
            BlockPos pos = playerPos.offset(dir);
            if (mc.world.getBlockState(pos).isReplaceable()) {
                mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND,
                        new BlockHitResult(Vec3d.ofCenter(pos), dir.getOpposite(), pos, false));
            }
        }

        mc.player.getInventory().selectedSlot = oldSlot;
    }
}
