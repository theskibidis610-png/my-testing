package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import net.minecraft.util.math.Vec3d;

public class Flight extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Flight mode", "Creative", "Creative", "Glide", "Jetpack"));

    public Flight() {
        super("Flight", "Alternative flight module", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        switch (mode.getValue()) {
            case "Creative" -> {
                mc.player.getAbilities().flying = true;
            }
            case "Glide" -> {
                if (!mc.player.isOnGround()) {
                    mc.player.setVelocity(mc.player.getVelocity().x, -0.05, mc.player.getVelocity().z);
                }
            }
            case "Jetpack" -> {
                if (mc.options.jumpKey.isPressed()) {
                    mc.player.setVelocity(mc.player.getVelocity().add(0, 0.15, 0));
                }
            }
        }
    }

    @Override
    protected void onDisable() {
        if (mc.player != null && !mc.player.isCreative()) {
            mc.player.getAbilities().flying = false;
        }
    }
}
