package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class AutoWalk extends Module {
    private final BooleanSetting sprint = addSetting(new BooleanSetting("Sprint", "Also sprint while walking", false));

    public AutoWalk() {
        super("AutoWalk", "Automatically walk forward", Category.PLAYER);
    }

    @Override
    protected void onDisable() {
        if (mc.player == null) return;

        mc.options.forwardKey.setPressed(false);
        if (sprint.getValue()) {
            mc.options.sprintKey.setPressed(false);
        }
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        // Simulate forward key press
        mc.options.forwardKey.setPressed(true);

        // Optionally also sprint
        if (sprint.getValue()) {
            mc.options.sprintKey.setPressed(true);
        } else {
            mc.options.sprintKey.setPressed(false);
        }
    }
}
