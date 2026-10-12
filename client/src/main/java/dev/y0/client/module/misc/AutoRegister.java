package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class AutoRegister extends Module {
    private final BooleanSetting autoLogin = addSetting(new BooleanSetting("Auto Login", "Automatically send /login", true));
    private final BooleanSetting autoReg = addSetting(new BooleanSetting("Auto Register", "Automatically send /register", true));

    private boolean hasSentCommand;
    private int tickDelay;

    public AutoRegister() {
        super("AutoRegister", "Auto /register and /login on servers", Category.MISC);
    }

    @Override
    protected void onEnable() {
        hasSentCommand = false;
        tickDelay = 0;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.getNetworkHandler() == null) return;
        if (hasSentCommand) return;

        // Wait a few ticks after joining before sending commands
        tickDelay++;
        if (tickDelay < 20) return;

        // NOTE: This is a placeholder. A real implementation should:
        // 1. Store passwords securely (e.g. encrypted config file)
        // 2. Detect server auth plugin messages (e.g. "Please /register" in chat)
        // 3. Use a mixin on ChatMessageC2SPacket or listen for chat events
        //
        // DO NOT store real passwords in code. This is for demonstration only.
        String placeholder = "changeme";

        if (autoReg.getValue()) {
            mc.player.networkHandler.sendChatCommand("register " + placeholder + " " + placeholder);
            Y0Client.LOGGER.info("AutoRegister: Sent /register command");
        }

        if (autoLogin.getValue()) {
            mc.player.networkHandler.sendChatCommand("login " + placeholder);
            Y0Client.LOGGER.info("AutoRegister: Sent /login command");
        }

        hasSentCommand = true;
    }
}
