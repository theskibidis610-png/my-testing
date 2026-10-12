package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class RailPlacer extends Module {
    private final BooleanSetting powered = addSetting(new BooleanSetting("Powered", "Place powered rails instead", false));

    public RailPlacer() {
        super("RailPlacer", "Auto place rails as you walk", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        var targetItem = powered.getValue() ? Items.POWERED_RAIL : Items.RAIL;
        int slot = -1;
        for (int i = 0; i < 9; i++) {
            if (mc.player.getInventory().getStack(i).isOf(targetItem)) { slot = i; break; }
        }
        if (slot == -1) return;

        BlockPos pos = mc.player.getBlockPos();
        if (mc.world.getBlockState(pos).isAir() && !mc.world.getBlockState(pos.down()).isAir()) {
            int prev = mc.player.getInventory().selectedSlot;
            mc.player.getInventory().selectedSlot = slot;
            BlockHitResult hit = new BlockHitResult(Vec3d.ofCenter(pos), Direction.UP, pos.down(), false);
            mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
            mc.player.getInventory().selectedSlot = prev;
        }
    }
}
