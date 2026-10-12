package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class Criticals extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Criticals method", "Packet", "Packet", "Jump"));

    public Criticals() {
        super("Criticals", "Always deal critical hits", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.interactionManager == null) return;

        if (mode.getValue().equals("Packet") && mc.player.isOnGround()) {
            if (mc.player.getAttacking() != null) {
                double x = mc.player.getX();
                double y = mc.player.getY();
                double z = mc.player.getZ();

                mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(x, y + 0.0625, z, false, mc.player.horizontalCollision));
                mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(x, y, z, false, mc.player.horizontalCollision));
            }
        }
    }
}
