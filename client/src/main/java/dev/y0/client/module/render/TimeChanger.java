package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import dev.y0.client.setting.NumberSetting;

public class TimeChanger extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Time of day preset", "Day", "Day", "Night", "Sunset", "Custom"));
    private final NumberSetting customTime = addSetting(new NumberSetting("Custom Time", "World time ticks (0-24000)", 6000.0, 0.0, 24000.0, 100.0));

    public TimeChanger() {
        super("TimeChanger", "Change client-side world time", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null) return;

        long time;
        switch (mode.getValue()) {
            case "Day":
                time = 6000;
                break;
            case "Night":
                time = 18000;
                break;
            case "Sunset":
                time = 12500;
                break;
            case "Custom":
                time = (long) customTime.getValue();
                break;
            default:
                time = 6000;
                break;
        }

        mc.world.setTimeOfDay(time);
    }
}
