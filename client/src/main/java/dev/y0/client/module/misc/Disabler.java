package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class Disabler extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Anti-cheat bypass mode", "Vanilla", "Vanilla", "Custom"));

    private int tickCounter;

    public Disabler() {
        super("Disabler", "Attempt to disable server anti-cheat", Category.MISC);
    }

    @Override
    protected void onEnable() {
        tickCounter = 0;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.getNetworkHandler() == null) return;

        tickCounter++;

        switch (mode.getValue()) {
            case "Vanilla" -> {
                // Send corrective position packets to confuse basic anti-cheat
                // by confirming the player's current position every few ticks
                if (tickCounter % 5 == 0) {
                    mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.Full(
                            mc.player.getX(),
                            mc.player.getY(),
                            mc.player.getZ(),
                            mc.player.getYaw(),
                            mc.player.getPitch(),
                            mc.player.isOnGround()
                    ));
                }
            }
            case "Custom" -> {
                // Send a ground-status correction packet on alternating ticks
                if (tickCounter % 2 == 0) {
                    mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));
                }
            }
        }
    }
}
