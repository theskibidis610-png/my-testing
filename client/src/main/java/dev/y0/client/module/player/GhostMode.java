package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.network.packet.Packet;

public class GhostMode extends Module {
    private final BooleanSetting render = addSetting(new BooleanSetting("Render", "Render ghost player at real position", true));
    private final List<Packet<?>> packetQueue = new ArrayList<>();

    public GhostMode() {
        super("GhostMode", "Queues packets to simulate ghost movement", Category.PLAYER);
    }

    @Override
    protected void onDisable() {
        if (mc.player == null) return;
        for (Packet<?> packet : packetQueue) {
            mc.player.networkHandler.getConnection().send(packet);
        }
        packetQueue.clear();
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Packet interception handled via mixin; packets stored in packetQueue
    }
}
