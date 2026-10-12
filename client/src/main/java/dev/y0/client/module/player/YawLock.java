package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;

public class YawLock extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Direction", "Lock yaw to this direction", "Nearest",
            "North", "South", "East", "West", "Nearest"));

    public YawLock() {
        super("YawLock", "Locks yaw to cardinal directions", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        float yaw;
        if (mode.getValue().equals("Nearest")) {
            yaw = snapToNearest(mc.player.getYaw());
        } else {
            yaw = getYawForDirection(mode.getValue());
        }

        mc.player.setYaw(yaw);
    }

    private float snapToNearest(float currentYaw) {
        float normalized = ((currentYaw % 360) + 360) % 360;
        if (normalized >= 315 || normalized < 45) return 0;    // South
        if (normalized >= 45 && normalized < 135) return 90;    // West (negative X)
        if (normalized >= 135 && normalized < 225) return 180;  // North
        return 270; // East (positive X)
    }

    private float getYawForDirection(String direction) {
        return switch (direction) {
            case "South" -> 0;
            case "West" -> 90;
            case "North" -> 180;
            case "East" -> -90;
            default -> 0;
        };
    }
}
