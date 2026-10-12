package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.block.Blocks;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class AutoSmelter extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Ticks between actions", 10.0, 1.0, 40.0, 1.0));
    private int tickCounter;

    public AutoSmelter() {
        super("AutoSmelter", "Auto manage blast furnace smelting", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;
        if (mc.currentScreen != null) return;
        if (++tickCounter < (int) delay.getValue()) return;
        tickCounter = 0;

        BlockPos center = mc.player.getBlockPos();
        for (BlockPos pos : BlockPos.iterateOutwards(center, 4, 4, 4)) {
            if (mc.world.getBlockState(pos).isOf(Blocks.BLAST_FURNACE)) {
                BlockHitResult hit = new BlockHitResult(Vec3d.ofCenter(pos), Direction.UP, pos, false);
                mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
                return;
            }
        }
    }
}
