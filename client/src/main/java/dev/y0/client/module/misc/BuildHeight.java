package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class BuildHeight extends Module {
    private final BooleanSetting display = addSetting(new BooleanSetting("Display", "Show build height on HUD", true));

    public BuildHeight() {
        super("BuildHeight", "Display current build height information", Category.MISC);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
    }

    public String getBuildHeightInfo() {
        if (mc.world == null || mc.player == null) return "N/A";
        int topY = mc.world.getTopY();
        int bottomY = mc.world.getBottomY();
        int playerY = (int) mc.player.getY();
        return String.format("Y: %d [%d to %d]", playerY, bottomY, topY);
    }

    public boolean shouldDisplay() {
        return display.getValue();
    }
}
