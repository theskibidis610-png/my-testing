package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class StashFinder extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Scan range for storage blocks", 32.0, 8.0, 64.0, 8.0));

    public StashFinder() {
        super("StashFinder", "Mark potential stash locations with clusters of storage blocks", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        int r = (int) range.getValue();
        BlockPos center = mc.player.getBlockPos();
        int storageCount = 0;

        for (BlockPos pos : BlockPos.iterateOutwards(center, r, r, r)) {
            var block = mc.world.getBlockState(pos).getBlock();
            if (block instanceof ChestBlock || block instanceof ShulkerBoxBlock) {
                storageCount++;
            }
        }

        if (storageCount >= 5) {
            mc.player.sendMessage(
                    Text.literal("StashFinder: " + storageCount + " storage blocks nearby!"), true
            );
        }
    }
}
