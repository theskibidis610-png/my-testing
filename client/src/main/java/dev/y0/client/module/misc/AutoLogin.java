package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class AutoLogin extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Ticks to wait before sending login command", 30.0, 10.0, 200.0, 1.0));

    private int tickCounter;
    private boolean loginSent;

    public AutoLogin() {
        super("AutoLogin", "Automatically login to cracked servers", Category.MISC);
    }

    @Override
    protected void onEnable() {
        tickCounter = 0;
        loginSent = false;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.getNetworkHandler() == null) return;
        if (loginSent) return;

        tickCounter++;
        if (tickCounter >= (int) delay.getValue()) {
            // Password would be stored securely in a full implementation
            mc.player.networkHandler.sendChatCommand("login password");
            loginSent = true;
        }
    }
}
