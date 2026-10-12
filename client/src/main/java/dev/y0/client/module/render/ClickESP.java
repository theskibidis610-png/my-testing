package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClickESP extends Module {
    private final NumberSetting duration = addSetting(new NumberSetting("Duration", "Display duration in seconds", 3.0, 0.5, 10.0, 0.5));

    private final List<long[]> clickPositions = new ArrayList<>();

    public ClickESP() {
        super("ClickESP", "Show where you clicked", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null) return;
        long now = System.currentTimeMillis();
        long durationMs = (long) (duration.getValue() * 1000);
        clickPositions.removeIf(entry -> now - entry[0] > durationMs);
    }

    public void addClick(BlockPos pos) {
        clickPositions.add(new long[]{System.currentTimeMillis(), pos.getX(), pos.getY(), pos.getZ()});
    }

    public List<long[]> getClickPositions() {
        return clickPositions;
    }
}
