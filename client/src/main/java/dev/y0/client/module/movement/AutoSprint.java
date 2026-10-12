package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;

public class AutoSprint extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Sprint mode", "Legit", "Legit", "Rage"));

    public AutoSprint() {
        super("AutoSprint", "Always sprint when moving", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        switch (mode.getValue()) {
            case "Legit" -> {
                if (mc.player.input.movementForward > 0 && !mc.player.isSneaking() && !mc.player.horizontalCollision) {
                    mc.player.setSprinting(true);
                }
            }
            case "Rage" -> {
                if (mc.player.input.movementForward != 0 || mc.player.input.movementSideways != 0) {
                    mc.player.setSprinting(true);
                }
            }
        }
    }
}
