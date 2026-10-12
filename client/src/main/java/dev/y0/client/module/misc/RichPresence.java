package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class RichPresence extends Module {
    private final BooleanSetting showServer = addSetting(new BooleanSetting("Show Server", "Include server address in presence", false));

    public RichPresence() {
        super("RichPresence", "Custom rich presence for external services", Category.MISC);
    }

    @Override
    protected void onEnable() {
        Y0Client.LOGGER.info("RichPresence enabled");
    }

    @Override
    protected void onDisable() {
        Y0Client.LOGGER.info("RichPresence disabled");
    }

    public String getPresenceText() {
        StringBuilder text = new StringBuilder("Playing with " + Y0Client.NAME);
        if (showServer.getValue() && mc.getCurrentServerEntry() != null) {
            text.append(" on ").append(mc.getCurrentServerEntry().address);
        }
        return text.toString();
    }
}
