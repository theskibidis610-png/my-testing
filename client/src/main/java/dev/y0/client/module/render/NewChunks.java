package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.util.math.ChunkPos;

import java.util.HashSet;
import java.util.Set;

public class NewChunks extends Module {
    private final BooleanSetting highlight = addSetting(new BooleanSetting("Highlight", "Highlight new chunks", true));

    private final Set<ChunkPos> newChunks = new HashSet<>();

    public NewChunks() {
        super("NewChunks", "Highlight newly generated chunks", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null) return;
        // Detect newly generated chunks by liquid flow patterns
        // Rendering handled via world render mixin
    }

    public void addNewChunk(ChunkPos pos) {
        newChunks.add(pos);
    }

    public Set<ChunkPos> getNewChunks() {
        return newChunks;
    }

    @Override
    protected void onDisable() {
        newChunks.clear();
    }
}
