package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;

public class SilentClose extends Module {

    public SilentClose() {
        super("SilentClose", "Closes containers without notifying the server", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Silent close logic: when the player presses escape on a container,
        // close the screen locally without sending CloseHandledScreenC2SPacket.
        // Requires mixin to intercept the close packet.
    }
}
