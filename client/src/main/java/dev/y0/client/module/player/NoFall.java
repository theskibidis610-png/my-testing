package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class NoFall extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "NoFall method", "Packet", "Packet", "MLG"));

    public NoFall() {
        super("NoFall", "Prevents fall damage", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (mode.getValue().equals("Packet")) {
            if (mc.player.fallDistance > 2.0f) {
                mc.player.networkHandler.sendPacket(
                        new PlayerMoveC2SPacket.OnGroundOnly(true, mc.player.horizontalCollision)
                );
            }
        }
    }
}
