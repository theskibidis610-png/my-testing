package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class AFKDisplay extends Module {
    private final BooleanSetting showMessage = addSetting(new BooleanSetting("Show Message", "Display AFK message in chat", true));

    private int idleTicks;

    public AFKDisplay() {
        super("AFKDisplay", "Show AFK status when idle", Category.MISC);
    }

    @Override
    protected void onEnable() {
        idleTicks = 0;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (mc.player.input.movementForward == 0 && mc.player.input.movementSideways == 0) {
            idleTicks++;
        } else {
            idleTicks = 0;
        }
    }

    public boolean isAFK() {
        return idleTicks > 600; // 30 seconds
    }
}
