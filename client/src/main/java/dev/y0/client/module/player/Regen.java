package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class Regen extends Module {
    private final NumberSetting packetsPerTick = addSetting(new NumberSetting("Packets", "Position packets sent per tick", 20.0, 1.0, 100.0, 1.0));

    public Regen() {
        super("Regen", "Sends packets to accelerate health regeneration", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.getHealth() >= mc.player.getMaxHealth()) return;
        if (mc.player.getHungerManager().getFoodLevel() < 18) return;
        if (!mc.player.isOnGround()) return;

        int count = (int) packetsPerTick.getValue();
        for (int i = 0; i < count; i++) {
            mc.player.networkHandler.sendPacket(
                    new PlayerMoveC2SPacket.OnGroundOnly(true, mc.player.horizontalCollision)
            );
        }
    }
}
