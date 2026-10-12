package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

import java.util.Random;

public class AntiAim extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Rotation mode", "Spin", "Spin", "Jitter", "Down"));

    private final Random random = new Random();
    private float spinAngle;

    public AntiAim() {
        super("AntiAim", "Sends fake rotation packets to confuse opponents", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        float yaw;
        float pitch;

        switch (mode.getValue()) {
            case "Jitter" -> {
                yaw = mc.player.getYaw() + (random.nextFloat() - 0.5f) * 180;
                pitch = mc.player.getPitch() + (random.nextFloat() - 0.5f) * 60;
            }
            case "Down" -> {
                yaw = mc.player.getYaw();
                pitch = 90;
            }
            default -> {
                spinAngle += 30;
                yaw = spinAngle;
                pitch = mc.player.getPitch();
            }
        }

        mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.LookAndOnGround(yaw, pitch, mc.player.isOnGround()));
    }
}
