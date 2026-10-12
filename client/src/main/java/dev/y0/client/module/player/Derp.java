package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import java.util.Random;

public class Derp extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Rotation speed multiplier", 1.0, 0.1, 5.0, 0.1));
    private final Random random = new Random();

    public Derp() {
        super("Derp", "Randomly rotates your head for others to see", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        float yaw = (random.nextFloat() - 0.5f) * 360.0f * speed.getValue().floatValue();
        float pitch = (random.nextFloat() - 0.5f) * 180.0f * speed.getValue().floatValue();
        mc.player.setYaw(mc.player.getYaw() + yaw);
        mc.player.setPitch(Math.max(-90, Math.min(90, mc.player.getPitch() + pitch)));
    }
}
