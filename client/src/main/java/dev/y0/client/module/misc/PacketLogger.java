package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class PacketLogger extends Module {
    private final BooleanSetting incoming = addSetting(new BooleanSetting("Incoming", "Log incoming packets", true));
    private final BooleanSetting outgoing = addSetting(new BooleanSetting("Outgoing", "Log outgoing packets", false));

    public PacketLogger() {
        super("PacketLogger", "Log network packets to chat", Category.MISC);
    }

    // NOTE: Full implementation requires a mixin on ClientConnection
    // to intercept send() for outgoing and channelRead0() for incoming packets.
    // Each intercepted packet name is sent to chat via mc.player.sendMessage().

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Placeholder: packet logging is handled by mixin callbacks.
        // This tick handler exists for the event bus registration.
    }

    public boolean logIncoming() {
        return isEnabled() && incoming.getValue();
    }

    public boolean logOutgoing() {
        return isEnabled() && outgoing.getValue();
    }
}
