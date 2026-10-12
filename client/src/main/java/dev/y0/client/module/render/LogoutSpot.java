package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LogoutSpot extends Module {
    private final BooleanSetting showName = addSetting(new BooleanSetting("Show Name", "Display player name at logout spot", true));

    private final Map<UUID, Vec3d> logoutSpots = new HashMap<>();
    private final Map<UUID, String> playerNames = new HashMap<>();

    public LogoutSpot() {
        super("LogoutSpot", "Mark where players logged out", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null) return;
        // Track player positions and detect logouts
        // Rendering handled via world render mixin
    }

    public void addLogout(UUID uuid, String name, Vec3d pos) {
        logoutSpots.put(uuid, pos);
        playerNames.put(uuid, name);
    }

    public Map<UUID, Vec3d> getLogoutSpots() {
        return logoutSpots;
    }

    public Map<UUID, String> getPlayerNames() {
        return playerNames;
    }
}
