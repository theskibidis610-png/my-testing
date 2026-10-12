package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.ChunkPos;

import java.util.HashSet;
import java.util.Set;

public class WorldDownload extends Module {
    private final NumberSetting radius = addSetting(new NumberSetting("Radius", "Chunk radius to download", 3.0, 1.0, 8.0, 1.0));
    private final Set<ChunkPos> savedChunks = new HashSet<>();

    public WorldDownload() {
        super("WorldDownload", "Download and save world chunks locally", Category.WORLD);
    }

    @Override
    protected void onEnable() {
        savedChunks.clear();
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        int r = (int) radius.getValue();
        ChunkPos center = mc.player.getChunkPos();

        for (int cx = -r; cx <= r; cx++) {
            for (int cz = -r; cz <= r; cz++) {
                ChunkPos cp = new ChunkPos(center.x + cx, center.z + cz);
                if (!savedChunks.contains(cp) && mc.world.getChunk(cp.x, cp.z) != null) {
                    savedChunks.add(cp);
                    // Chunk data would be serialized to disk here
                }
            }
        }
    }
}
