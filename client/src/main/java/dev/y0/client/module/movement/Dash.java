package dev.y0.client.module.movement;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

public class Dash extends Module {
    private final NumberSetting strength = addSetting(new NumberSetting("Strength", "Dash strength", 3.0, 1.0, 5.0, 0.5));

    public Dash() {
        super("Dash", "Quick dash forward", Category.MOVEMENT);
    }

    @Override
    protected void onEnable() {
        if (mc.player == null) {
            disable();
            return;
        }

        float yaw = mc.player.getYaw();
        double rad = Math.toRadians(yaw);
        double spd = strength.getValue();
        mc.player.setVelocity(new Vec3d(-Math.sin(rad) * spd, mc.player.getVelocity().y + 0.2, Math.cos(rad) * spd));
        disable();
    }
}
