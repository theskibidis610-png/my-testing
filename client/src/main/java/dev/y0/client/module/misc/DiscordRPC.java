package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class DiscordRPC extends Module {
    private final BooleanSetting showServer = addSetting(new BooleanSetting("Show Server", "Display server IP in presence", false));
    private final BooleanSetting showModuleCount = addSetting(new BooleanSetting("Show Module Count", "Display active module count", true));

    public DiscordRPC() {
        super("DiscordRPC", "Discord rich presence integration", Category.MISC);
    }

    @Override
    protected void onEnable() {
        // Placeholder: Initialize Discord RPC connection
        // A full implementation would use discord-rpc or JNA to connect
        // to the Discord client via IPC.
        Y0Client.LOGGER.info("DiscordRPC enabled - would connect to Discord");
    }

    @Override
    protected void onDisable() {
        // Placeholder: Shutdown Discord RPC connection
        Y0Client.LOGGER.info("DiscordRPC disabled - would disconnect from Discord");
    }

    public String getDetails() {
        String details = "Playing with " + Y0Client.NAME;
        if (showModuleCount.getValue()) {
            long count = Y0Client.getInstance().getModuleManager().getModules().stream()
                    .filter(Module::isEnabled).count();
            details += " (" + count + " modules)";
        }
        return details;
    }

    public String getState() {
        if (showServer.getValue() && mc.getCurrentServerEntry() != null) {
            return mc.getCurrentServerEntry().address;
        }
        return "Singleplayer";
    }
}
