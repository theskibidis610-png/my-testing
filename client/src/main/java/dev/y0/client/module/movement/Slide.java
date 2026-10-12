package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

public class Slide extends Module {
    private final NumberSetting friction = addSetting(new NumberSetting("Friction", "Slide friction (lower = more slide)", 0.98, 0.9, 1.0, 0.01));

    public Slide() {
        super("Slide", "Slide downhill with momentum", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.isOnGround()) {
            Vec3d vel = mc.player.getVelocity();
            mc.player.setVelocity(vel.x * friction.getValue(), vel.y, vel.z * friction.getValue());
        }
    }
}
