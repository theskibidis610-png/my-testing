package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.Map;

public class Waypoints extends Module {
    private final BooleanSetting showDistance = addSetting(new BooleanSetting("Show Distance", "Display distance to waypoint", true));
    private final BooleanSetting showName = addSetting(new BooleanSetting("Show Name", "Display waypoint name", true));

    private final Map<String, Vec3d> waypoints = new HashMap<>();

    public Waypoints() {
        super("Waypoints", "Show waypoint markers", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null || mc.player == null) return;
        // Waypoint rendering handled via HUD/world render mixin
    }

    public void addWaypoint(String name, Vec3d pos) {
        waypoints.put(name, pos);
    }

    public void removeWaypoint(String name) {
        waypoints.remove(name);
    }

    public Map<String, Vec3d> getWaypoints() {
        return waypoints;
    }

    public boolean shouldShowDistance() {
        return showDistance.getValue();
    }

    public boolean shouldShowName() {
        return showName.getValue();
    }
}
