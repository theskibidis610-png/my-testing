package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class NoWeather extends Module {
    private final BooleanSetting rain = addSetting(new BooleanSetting("Rain", "Disable rain rendering", true));
    private final BooleanSetting thunder = addSetting(new BooleanSetting("Thunder", "Disable thunder effects", true));

    public NoWeather() {
        super("NoWeather", "Disable rain/snow rendering", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null) return;
        if (rain.getValue()) {
            mc.world.setRainGradient(0.0f);
        }
        if (thunder.getValue()) {
            mc.world.setThunderGradient(0.0f);
        }
    }
}
