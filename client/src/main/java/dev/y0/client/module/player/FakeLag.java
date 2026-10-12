package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.network.packet.Packet;

public class FakeLag extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Lag delay in ticks", 10.0, 1.0, 100.0, 1.0));
    private final List<Packet<?>> packetBuffer = new ArrayList<>();
    private int tickCounter;

    public FakeLag() {
        super("FakeLag", "Simulates network lag by delaying packets", Category.PLAYER);
    }

    @Override
    protected void onDisable() {
        if (mc.player == null) return;
        for (Packet<?> packet : packetBuffer) {
            mc.player.networkHandler.getConnection().send(packet);
        }
        packetBuffer.clear();
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        tickCounter++;
        if (tickCounter >= delay.getValue() && !packetBuffer.isEmpty()) {
            mc.player.networkHandler.getConnection().send(packetBuffer.remove(0));
            tickCounter = 0;
        }
    }
}
