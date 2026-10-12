package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class WorldInfo extends Module {
    private final BooleanSetting seed = addSetting(new BooleanSetting("Seed", "Display world seed if known", false));
    private final BooleanSetting time = addSetting(new BooleanSetting("Time", "Display world time on HUD", true));

    public WorldInfo() {
        super("WorldInfo", "Display world information on HUD", Category.MISC);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null) return;
    }

    public String getTimeInfo() {
        if (mc.world == null) return "N/A";
        long worldTime = mc.world.getTimeOfDay() % 24000;
        int hours = (int) ((worldTime / 1000 + 6) % 24);
        int minutes = (int) ((worldTime % 1000) * 60 / 1000);
        return String.format("%02d:%02d", hours, minutes);
    }

    public boolean showSeed() {
        return seed.getValue();
    }

    public boolean showTime() {
        return time.getValue();
    }
}
