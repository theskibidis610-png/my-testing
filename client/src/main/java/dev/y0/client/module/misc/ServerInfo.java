package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class ServerInfo extends Module {
    private final BooleanSetting tps = addSetting(new BooleanSetting("TPS", "Show server TPS on HUD", true));

    public ServerInfo() {
        super("ServerInfo", "Display server information on HUD", Category.MISC);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.getNetworkHandler() == null) return;
    }

    public String getServerAddress() {
        if (mc.getCurrentServerEntry() != null) {
            return mc.getCurrentServerEntry().address;
        }
        return "Singleplayer";
    }

    public boolean showTPS() {
        return tps.getValue();
    }
}
