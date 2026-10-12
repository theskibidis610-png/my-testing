package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import org.lwjgl.glfw.GLFW;

public class Sprint extends Module {
    private final BooleanSetting omnidirectional = addSetting(new BooleanSetting("Omni", "Sprint in all directions", true));

    public Sprint() {
        super("Sprint", "Automatically sprints", Category.MOVEMENT, GLFW.GLFW_KEY_X);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (omnidirectional.getValue()) {
            mc.player.setSprinting(mc.player.input.movementForward != 0 || mc.player.input.movementSideways != 0);
        } else {
            if (mc.player.input.movementForward > 0) {
                mc.player.setSprinting(true);
            }
        }
    }
}
