package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class BlockESP extends Module {
    private final ModeSetting target = addSetting(new ModeSetting("Target", "Block type to highlight", "Spawner", "Spawner", "Portal", "Chest"));
    private final List<BlockPos> foundBlocks = new ArrayList<>();

    public BlockESP() {
        super("BlockESP", "Highlight specific blocks in the world", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        foundBlocks.clear();
        int r = 32;
        BlockPos center = mc.player.getBlockPos();

        for (BlockPos pos : BlockPos.iterateOutwards(center, r, r, r)) {
            boolean match = switch (target.getValue()) {
                case "Spawner" -> mc.world.getBlockState(pos).isOf(Blocks.SPAWNER);
                case "Portal" -> mc.world.getBlockState(pos).isOf(Blocks.NETHER_PORTAL);
                case "Chest" -> mc.world.getBlockState(pos).isOf(Blocks.CHEST);
                default -> false;
            };
            if (match) foundBlocks.add(pos.toImmutable());
        }
    }

    public List<BlockPos> getFoundBlocks() { return foundBlocks; }
}
