package dev.y0.client.module.world;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

/**
 * Teleports the player above the nether roof (Y=128) using
 * a packet exploit. Only works in the nether dimension.
 */
public class NetherRoof extends Module {

    public NetherRoof() {
        super("NetherRoof", "Get on top of the nether roof", Category.WORLD);
    }

    @Override
    protected void onEnable() {
        if (mc.player == null || mc.getNetworkHandler() == null) {
            disable();
            return;
        }

        // Send position packet above bedrock roof
        mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(
                mc.player.getX(), 129.0, mc.player.getZ(), false));
        mc.player.setPosition(mc.player.getX(), 129.0, mc.player.getZ());
        disable();
    }
}
