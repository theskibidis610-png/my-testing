package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.Vec3d;

public class PacketFly extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Flight speed multiplier", 1.0, 0.1, 5.0, 0.1));

    public PacketFly() {
        super("PacketFly", "Fly using position packets", Category.PLAYER);
    }

    @Override
    protected void onDisable() {
        if (mc.player != null) {
            mc.player.setVelocity(Vec3d.ZERO);
        }
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        mc.player.setVelocity(Vec3d.ZERO);
        mc.player.noClip = true;

        double x = mc.player.getX();
        double y = mc.player.getY();
        double z = mc.player.getZ();
        double s = speed.getValue() * 0.0624;

        if (mc.options.forwardKey.isPressed()) {
            x -= Math.sin(Math.toRadians(mc.player.getYaw())) * s;
            z += Math.cos(Math.toRadians(mc.player.getYaw())) * s;
        }
        if (mc.options.backKey.isPressed()) {
            x += Math.sin(Math.toRadians(mc.player.getYaw())) * s;
            z -= Math.cos(Math.toRadians(mc.player.getYaw())) * s;
        }
        if (mc.options.jumpKey.isPressed()) y += s;
        if (mc.options.sneakKey.isPressed()) y -= s;

        mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(x, y, z, false, mc.player.horizontalCollision));
        mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(x, y - 0.04, z, true, mc.player.horizontalCollision));

        mc.player.setPosition(x, y, z);
    }
}
