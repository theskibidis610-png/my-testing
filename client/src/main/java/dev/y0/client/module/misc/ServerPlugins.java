package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.network.packet.c2s.play.RequestCommandCompletionsC2SPacket;

public class ServerPlugins extends Module {

    public ServerPlugins() {
        super("ServerPlugins", "List server plugins via tab complete", Category.MISC);
    }

    @Override
    protected void onEnable() {
        if (mc.player == null || mc.getNetworkHandler() == null) {
            setEnabled(false);
            return;
        }

        // Send a tab-complete request for "/" to trigger the server
        // to respond with its available commands, which can reveal installed plugins.
        // Many Bukkit/Spigot servers expose plugin commands through tab completion.
        mc.getNetworkHandler().sendPacket(new RequestCommandCompletionsC2SPacket(0, "/"));

        Y0Client.LOGGER.info("ServerPlugins: Sent tab-complete request");
        Y0Client.LOGGER.info("ServerPlugins: Check logs for command completion results");

        // NOTE: To capture the response, a mixin on
        // ClientPlayNetworkHandler.onCommandSuggestions() is needed
        // to intercept CommandSuggestionsS2CPacket and parse the returned
        // command list. Plugin names can often be extracted from command
        // prefixes (e.g., /pluginname:command).

        // Disable after sending the request (one-shot module)
        setEnabled(false);
    }
}
