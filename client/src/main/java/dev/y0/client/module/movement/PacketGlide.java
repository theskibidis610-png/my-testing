package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class PacketGlide extends Module {
    private final NumberSetting fallSpeed = addSetting(new NumberSetting("Fall Speed", "Glide fall speed", 0.05, 0.01, 0.3, 0.01));

    public PacketGlide() {
        super("PacketGlide", "Glide using movement packets", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.getNetworkHandler() == null) return;
        if (mc.player.isOnGround()) return;

        mc.player.setVelocity(mc.player.getVelocity().x * 0.98, -fallSpeed.getValue(), mc.player.getVelocity().z * 0.98);
        mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(
                mc.player.getX(), mc.player.getY(), mc.player.getZ(), false, mc.player.horizontalCollision
        ));
    }
}
