package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

public class WaterDash extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Water dash speed", 2.0, 1.0, 5.0, 0.5));

    public WaterDash() {
        super("WaterDash", "Dash through water at high speed", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.isTouchingWater() && mc.player.input.movementForward > 0) {
            Vec3d look = mc.player.getRotationVector();
            mc.player.setVelocity(
                    look.x * speed.getValue() * 0.3,
                    look.y * speed.getValue() * 0.15,
                    look.z * speed.getValue() * 0.3
            );
        }
    }
}
