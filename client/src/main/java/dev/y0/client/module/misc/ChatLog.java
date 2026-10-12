package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class ChatLog extends Module {
    private final BooleanSetting timestamps = addSetting(new BooleanSetting("Timestamps", "Include timestamps in log entries", true));

    public ChatLog() {
        super("ChatLog", "Log all chat messages to a file", Category.MISC);
    }

    @Override
    protected void onEnable() {
        // In a full implementation, this would open a FileWriter to
        // a log file in the client's config directory.
        Y0Client.LOGGER.info("ChatLog enabled - logging chat messages");
    }

    @Override
    protected void onDisable() {
        Y0Client.LOGGER.info("ChatLog disabled - log file closed");
    }

    public boolean useTimestamps() {
        return timestamps.getValue();
    }
}
