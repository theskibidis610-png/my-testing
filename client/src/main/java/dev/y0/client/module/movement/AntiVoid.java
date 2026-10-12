package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class AntiVoid extends Module {
    private final NumberSetting fallDist = addSetting(new NumberSetting("Max Fall", "Max fall distance before save", 10.0, 3.0, 30.0, 1.0));
    private double lastSafeY;

    public AntiVoid() {
        super("AntiVoid", "Prevent falling into void", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.isOnGround()) {
            lastSafeY = mc.player.getY();
        }
        if (mc.player.getY() < mc.world.getBottomY() + 2 && mc.player.fallDistance > fallDist.getValue()) {
            mc.player.setVelocity(0, 0.5, 0);
            mc.player.setPosition(mc.player.getX(), lastSafeY, mc.player.getZ());
        }
    }
}
