package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class CityESP extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Detection range", 10.0, 1.0, 20.0, 1.0));

    private final List<BlockPos> cityBlocks = new ArrayList<>();

    public CityESP() {
        super("CityESP", "Show breakable surround blocks", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null || mc.player == null) return;
        cityBlocks.clear();
        // Scan for breakable surround blocks around nearby players within range
        // Rendering handled via world render mixin
    }

    public List<BlockPos> getCityBlocks() {
        return cityBlocks;
    }
}
