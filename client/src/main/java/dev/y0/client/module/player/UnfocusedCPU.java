package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class UnfocusedCPU extends Module {
    private final NumberSetting fps = addSetting(new NumberSetting("FPS", "Max FPS when unfocused", 15.0, 1.0, 60.0, 1.0));

    public UnfocusedCPU() {
        super("UnfocusedCPU", "Reduces CPU usage when the game window is unfocused", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (!mc.isWindowFocused()) {
            mc.getWindow().setFramerateLimit(fps.getValue().intValue());
        }
    }

    @Override
    protected void onDisable() {
        // Restore the user's configured framerate limit
        mc.getWindow().setFramerateLimit(mc.options.getMaxFps().getValue());
    }
}
