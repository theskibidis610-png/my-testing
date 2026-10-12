package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class MemoryDisplay extends Module {
    private final BooleanSetting percentage = addSetting(new BooleanSetting("Percentage", "Show memory as percentage instead of MB", false));

    public MemoryDisplay() {
        super("MemoryDisplay", "Display memory usage on HUD", Category.MISC);
    }

    @EventHandler
    public void onTick(EventTick event) {
        // Memory info rendered in HUD overlay
    }

    public String getMemoryInfo() {
        long max = Runtime.getRuntime().maxMemory();
        long total = Runtime.getRuntime().totalMemory();
        long free = Runtime.getRuntime().freeMemory();
        long used = total - free;

        if (percentage.getValue()) {
            return String.format("Mem: %d%%", (used * 100) / max);
        }
        return String.format("Mem: %dMB / %dMB", used / 1048576, max / 1048576);
    }
}
