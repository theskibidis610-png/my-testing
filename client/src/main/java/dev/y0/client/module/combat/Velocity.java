package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import org.lwjgl.glfw.GLFW;

public class Velocity extends Module {
    private final NumberSetting horizontal = addSetting(new NumberSetting("Horizontal", "Horizontal knockback multiplier", 0.0, 0.0, 100.0, 1.0));
    private final NumberSetting vertical = addSetting(new NumberSetting("Vertical", "Vertical knockback multiplier", 0.0, 0.0, 100.0, 1.0));

    public Velocity() {
        super("Velocity", "Reduces knockback taken", Category.COMBAT, GLFW.GLFW_KEY_V);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (mc.player.hurtTime > 0) {
            double hMult = horizontal.getValue() / 100.0;
            double vMult = vertical.getValue() / 100.0;

            mc.player.setVelocity(
                    mc.player.getVelocity().x * hMult,
                    mc.player.getVelocity().y * vMult,
                    mc.player.getVelocity().z * hMult
            );
        }
    }
}
