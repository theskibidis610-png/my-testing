package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class TorchPlace extends Module {
    private final NumberSetting lightThreshold = addSetting(new NumberSetting("Threshold", "Light level threshold to place torch", 3.0, 0.0, 7.0, 1.0));

    public TorchPlace() {
        super("TorchPlace", "Auto place torches in dark areas", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        // Find torch in hotbar
        int torchSlot = -1;
        for (int i = 0; i < 9; i++) {
            var item = mc.player.getInventory().getStack(i).getItem();
            if (item == Items.TORCH || item == Items.SOUL_TORCH) {
                torchSlot = i;
                break;
            }
        }
        if (torchSlot == -1) return;

        BlockPos feet = mc.player.getBlockPos();
        int threshold = (int) lightThreshold.getValue();

        if (mc.world.getLightLevel(feet) <= threshold && mc.world.getBlockState(feet).isAir()
                && !mc.world.getBlockState(feet.down()).isAir()) {
            int prevSlot = mc.player.getInventory().selectedSlot;
            mc.player.getInventory().selectedSlot = torchSlot;

            BlockHitResult hit = new BlockHitResult(Vec3d.ofCenter(feet), Direction.UP, feet.down(), false);
            mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);

            mc.player.getInventory().selectedSlot = prevSlot;
        }
    }
}
