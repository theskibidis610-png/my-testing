package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class ChunkLoader extends Module {
    private final NumberSetting radius = addSetting(new NumberSetting("Radius", "Chunk loading radius", 2.0, 1.0, 8.0, 1.0));
    private int tickCounter;

    public ChunkLoader() {
        super("ChunkLoader", "Keep chunks loaded around you", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.getNetworkHandler() == null) return;
        if (++tickCounter < 20) return;
        tickCounter = 0;

        // Send periodic position packets to keep chunks in loading range
        double x = mc.player.getX();
        double z = mc.player.getZ();
        double r = radius.getValue() * 16;

        mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(
                x + r, mc.player.getY(), z, mc.player.isOnGround()));
        mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(
                x, mc.player.getY(), z, mc.player.isOnGround()));
    }
}
