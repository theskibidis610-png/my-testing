package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class WitherBuilder extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Ticks between block placements", 2.0, 0.0, 10.0, 1.0));
    private int tickCounter;
    private int step;

    // Wither pattern offsets: soul sand base (4), then skulls (3)
    private static final BlockPos[] SOUL_SAND_OFFSETS = {
            new BlockPos(0, 0, 0),    // base center
            new BlockPos(0, 1, 0),    // middle
            new BlockPos(-1, 1, 0),   // left arm
            new BlockPos(1, 1, 0)     // right arm
    };

    public WitherBuilder() {
        super("WitherBuilder", "Auto build withers with configurable delay", Category.WORLD);
    }

    @Override
    protected void onEnable() {
        step = 0;
        tickCounter = 0;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        tickCounter++;
        if (tickCounter < (int) delay.getValue()) return;
        tickCounter = 0;

        if (step >= SOUL_SAND_OFFSETS.length) {
            disable();
            return;
        }

        // Find soul sand in hotbar
        int ssSlot = findBlockSlot(Blocks.SOUL_SAND);
        if (ssSlot == -1) return;

        BlockPos base = mc.player.getBlockPos().offset(Direction.NORTH, 3);
        BlockPos target = base.add(SOUL_SAND_OFFSETS[step]);

        if (mc.world.getBlockState(target).isAir()) {
            int prevSlot = mc.player.getInventory().selectedSlot;
            mc.player.getInventory().selectedSlot = ssSlot;

            BlockHitResult hit = new BlockHitResult(Vec3d.ofCenter(target), Direction.UP, target.down(), false);
            mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);

            mc.player.getInventory().selectedSlot = prevSlot;
        }
        step++;
    }

    private int findBlockSlot(net.minecraft.block.Block block) {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.player.getInventory().getStack(i);
            if (stack.getItem() instanceof BlockItem bi && bi.getBlock() == block) {
                return i;
            }
        }
        return -1;
    }
}
