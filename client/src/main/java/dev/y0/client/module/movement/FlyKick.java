package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class FlyKick extends Module {
    private final BooleanSetting groundSpoof = addSetting(new BooleanSetting("Ground Spoof", "Spoof ground state to bypass fly kick", true));
    private int tickCounter;

    public FlyKick() {
        super("FlyKick", "Bypass server fly kick detection", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.getNetworkHandler() == null) return;
        if (!groundSpoof.getValue()) return;

        tickCounter++;
        if (tickCounter >= 20) {
            mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true, mc.player.horizontalCollision));
            tickCounter = 0;
        }
    }
}
