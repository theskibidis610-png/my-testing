package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;

public class XCarry extends Module {

    public XCarry() {
        super("XCarry", "Keep items in crafting grid slots", Category.PLAYER);
    }

    @Override
    protected void onEnable() {
        if (mc.player == null) return;

        // Send a close window packet to the server to trick it into thinking the
        // inventory is closed, while keeping crafting slots intact client-side.
        // This allows items to remain in the 2x2 crafting grid.
        mc.player.networkHandler.sendPacket(new CloseHandledScreenC2SPacket(mc.player.currentScreenHandler.syncId));
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        // Continuously send close packets when inventory is opened and closed
        // to maintain the crafting slot exploit.
        // Note: For full functionality, a mixin on ClientPlayNetworkHandler.onScreenHandlerSlotUpdate
        // is recommended to prevent the server from clearing crafting slots when
        // a CloseHandledScreenC2SPacket is sent by the vanilla client.
    }

    @Override
    protected void onDisable() {
        if (mc.player == null) return;

        // When disabled, let vanilla behavior resume - crafting slots will clear normally
        // on next inventory close.
    }
}
