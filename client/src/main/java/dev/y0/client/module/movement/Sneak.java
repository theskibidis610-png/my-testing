package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;

public class Sneak extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Sneak method", "Packet", "Packet", "Vanilla"));

    public Sneak() {
        super("Sneak", "Automatically sneak", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mode.getValue().equals("Packet")) {
            mc.player.networkHandler.sendPacket(
                    new ClientCommandC2SPacket(mc.player, ClientCommandC2SPacket.Mode.PRESS_SHIFT_KEY)
            );
        } else {
            mc.player.setSneaking(true);
        }
    }

    @Override
    protected void onDisable() {
        if (mc.player != null) {
            mc.player.networkHandler.sendPacket(
                    new ClientCommandC2SPacket(mc.player, ClientCommandC2SPacket.Mode.RELEASE_SHIFT_KEY)
            );
            mc.player.setSneaking(false);
        }
    }
}
