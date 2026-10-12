package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class Desyncer extends Module {
    private final NumberSetting offset = addSetting(new NumberSetting("Offset", "Desync offset distance", 0.5, 0.1, 3.0, 0.1));

    public Desyncer() {
        super("Desyncer", "Desync position for combat", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        double x = mc.player.getX();
        double y = mc.player.getY();
        double z = mc.player.getZ();
        mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(
                x + offset.getValue(), y, z, mc.player.isOnGround(), mc.player.horizontalCollision));
    }
}
