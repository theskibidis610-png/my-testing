package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class KeyStrokes extends Module {
    private final BooleanSetting wasd = addSetting(new BooleanSetting("WASD", "Show WASD key presses", true));

    public KeyStrokes() {
        super("KeyStrokes", "Display key presses on the HUD", Category.MISC);
    }

    @EventHandler
    public void onTick(EventTick event) {
        // Key press rendering is handled in HUD render event.
        // This tick handler provides state for the renderer.
    }

    public boolean showWASD() {
        return wasd.getValue();
    }

    public boolean isForwardPressed() {
        return mc.options.forwardKey.isPressed();
    }

    public boolean isBackPressed() {
        return mc.options.backKey.isPressed();
    }

    public boolean isLeftPressed() {
        return mc.options.leftKey.isPressed();
    }

    public boolean isRightPressed() {
        return mc.options.rightKey.isPressed();
    }
}
