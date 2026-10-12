package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class MessageLogger extends Module {
    private final BooleanSetting incoming = addSetting(new BooleanSetting("Incoming", "Log incoming private messages", true));

    public MessageLogger() {
        super("MessageLogger", "Log private messages to a file", Category.MISC);
    }

    @Override
    protected void onEnable() {
        // In a full implementation this opens a log file for writing.
        // Messages are captured by a mixin on incoming chat packets
        // that match /msg or /tell patterns.
        Y0Client.LOGGER.info("MessageLogger enabled - logging PMs");
    }

    @Override
    protected void onDisable() {
        Y0Client.LOGGER.info("MessageLogger disabled");
    }

    public boolean logIncoming() {
        return incoming.getValue();
    }
}
