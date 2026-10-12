package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

public class RocketBoost extends Module {
    private final NumberSetting power = addSetting(new NumberSetting("Power", "Rocket boost power", 2.0, 0.5, 5.0, 0.5));

    public RocketBoost() {
        super("RocketBoost", "Boost forward with firework rockets", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.isFallFlying() && mc.options.jumpKey.isPressed()) {
            Vec3d look = mc.player.getRotationVector();
            mc.player.addVelocity(look.x * power.getValue() * 0.1, look.y * power.getValue() * 0.1, look.z * power.getValue() * 0.1);
        }
    }
}
