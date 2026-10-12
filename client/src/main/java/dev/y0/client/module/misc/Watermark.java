package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class Watermark extends Module {
    private final BooleanSetting showVersion = addSetting(new BooleanSetting("Show Version", "Include version in watermark", true));

    public Watermark() {
        super("Watermark", "Show Y0 watermark on HUD", Category.MISC);
    }

    @EventHandler
    public void onTick(EventTick event) {
        // Watermark rendering is handled in a HUD render event.
        // This tick handler keeps the module registered on the event bus.
    }

    public String getWatermarkText() {
        if (showVersion.getValue()) {
            return Y0Client.NAME + " v" + Y0Client.VERSION;
        }
        return Y0Client.NAME;
    }
}
