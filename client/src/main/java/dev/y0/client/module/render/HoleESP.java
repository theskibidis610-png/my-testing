package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class HoleESP extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Scan range", 8.0, 1.0, 20.0, 1.0));
    private final BooleanSetting obsidian = addSetting(new BooleanSetting("Obsidian", "Show obsidian holes", true));
    private final BooleanSetting bedrock = addSetting(new BooleanSetting("Bedrock", "Show bedrock holes", true));

    private final List<BlockPos> holes = new ArrayList<>();

    public HoleESP() {
        super("HoleESP", "Highlight safe holes for crystal PVP", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null || mc.player == null) return;
        holes.clear();
        // Scan for 1x1 holes surrounded by obsidian/bedrock within range
        // Rendering handled via world render mixin
    }

    public List<BlockPos> getHoles() {
        return holes;
    }
}
