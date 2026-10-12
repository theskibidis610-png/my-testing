package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

public class Breadcrumbs extends Module {
    private final NumberSetting maxPoints = addSetting(new NumberSetting("Max Points", "Maximum trail positions stored", 1000.0, 100.0, 5000.0, 100.0));
    private final BooleanSetting rainbow = addSetting(new BooleanSetting("Rainbow", "Cycle trail color through rainbow", false));

    private final List<Vec3d> positions = new ArrayList<>();

    public Breadcrumbs() {
        super("Breadcrumbs", "Leave a trail behind the player", Category.RENDER);
    }

    @Override
    protected void onEnable() {
        positions.clear();
    }

    @Override
    protected void onDisable() {
        positions.clear();
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        Vec3d pos = mc.player.getPos();
        if (positions.isEmpty() || positions.get(positions.size() - 1).squaredDistanceTo(pos) > 0.25) {
            positions.add(pos);
        }

        int max = (int) maxPoints.getValue();
        while (positions.size() > max) {
            positions.remove(0);
        }

        // Actual line rendering requires a WorldRenderEvent mixin to draw
        // GL lines between consecutive positions. Use rainbow.getValue()
        // to cycle hue per segment when enabled.
    }

    public List<Vec3d> getPositions() {
        return positions;
    }

    public boolean isRainbow() {
        return rainbow.getValue();
    }
}
