package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.block.BlockState;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class OreFinder extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Scan range", 16.0, 4.0, 32.0, 1.0));
    private final List<BlockPos> ores = new ArrayList<>();

    public OreFinder() {
        super("OreFinder", "Find exposed ores by scanning nearby blocks", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        ores.clear();
        int r = (int) range.getValue();
        BlockPos center = mc.player.getBlockPos();

        for (BlockPos pos : BlockPos.iterateOutwards(center, r, r, r)) {
            BlockState state = mc.world.getBlockState(pos);
            if (state.isIn(BlockTags.GOLD_ORES) || state.isIn(BlockTags.DIAMOND_ORES)
                    || state.isIn(BlockTags.IRON_ORES) || state.isIn(BlockTags.COAL_ORES)) {
                ores.add(pos.toImmutable());
            }
        }
    }

    public List<BlockPos> getOres() { return ores; }
}
