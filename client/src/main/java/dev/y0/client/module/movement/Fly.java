package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

public class Fly extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Fly method", "Vanilla", "Vanilla", "Glide"));
    private final NumberSetting flySpeed = addSetting(new NumberSetting("Speed", "Flight speed", 2.0, 0.5, 10.0, 0.5));

    public Fly() {
        super("Fly", "Allows you to fly", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        switch (mode.getValue()) {
            case "Vanilla" -> {
                mc.player.getAbilities().flying = true;
                mc.player.getAbilities().setFlySpeed((float) (flySpeed.getValue() * 0.05));
            }
            case "Glide" -> {
                if (!mc.player.isOnGround()) {
                    mc.player.setVelocity(mc.player.getVelocity().x, -0.04, mc.player.getVelocity().z);
                }
            }
        }
    }

    @Override
    protected void onDisable() {
        if (mc.player != null && !mc.player.isCreative()) {
            mc.player.getAbilities().flying = false;
            mc.player.getAbilities().setFlySpeed(0.05f);
        }
    }
}
