package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

public class DolphinSwim extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Swimming speed boost", 1.5, 1.0, 5.0, 0.1));

    public DolphinSwim() {
        super("DolphinSwim", "Swim like a dolphin", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (!mc.player.isTouchingWater()) return;

        Vec3d look = mc.player.getRotationVector();
        double forward = mc.player.input.movementForward;
        if (forward > 0) {
            mc.player.setVelocity(
                    look.x * speed.getValue() * 0.3,
                    look.y * speed.getValue() * 0.3,
                    look.z * speed.getValue() * 0.3
            );
        }
    }
}
