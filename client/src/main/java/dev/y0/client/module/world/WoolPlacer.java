package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class WoolPlacer extends Module {
    private final ModeSetting color = addSetting(new ModeSetting("Color", "Wool color to place", "White", "White", "Red", "Blue"));

    public WoolPlacer() {
        super("WoolPlacer", "Auto place colored wool", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        var targetItem = switch (color.getValue()) {
            case "Red" -> Items.RED_WOOL;
            case "Blue" -> Items.BLUE_WOOL;
            default -> Items.WHITE_WOOL;
        };

        int slot = -1;
        for (int i = 0; i < 9; i++) {
            if (mc.player.getInventory().getStack(i).isOf(targetItem)) { slot = i; break; }
        }
        if (slot == -1) return;

        BlockPos pos = mc.player.getBlockPos().down();
        if (mc.world.getBlockState(pos).isAir()) {
            int prev = mc.player.getInventory().selectedSlot;
            mc.player.getInventory().selectedSlot = slot;
            BlockHitResult hit = new BlockHitResult(Vec3d.ofCenter(pos), Direction.UP, pos.down(), false);
            mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
            mc.player.getInventory().selectedSlot = prev;
        }
    }
}
