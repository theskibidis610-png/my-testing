package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class CritChain extends Module {
    private final BooleanSetting jumpCrit = addSetting(new BooleanSetting("Jump Crit", "Use jump-based crits", false));

    public CritChain() {
        super("CritChain", "Chain critical hits", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.player.getAttacking() == null) return;
        if (jumpCrit.getValue()) {
            if (mc.player.isOnGround()) mc.player.jump();
        } else {
            double x = mc.player.getX(), y = mc.player.getY(), z = mc.player.getZ();
            mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(x, y + 0.1, z, false, mc.player.horizontalCollision));
            mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(x, y, z, false, mc.player.horizontalCollision));
        }
    }
}
