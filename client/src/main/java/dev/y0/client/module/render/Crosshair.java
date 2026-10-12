package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import dev.y0.client.setting.ModeSetting;
import dev.y0.client.setting.NumberSetting;

public class Crosshair extends Module {
    private final ModeSetting style = addSetting(new ModeSetting("Style", "Crosshair shape", "Cross", "Cross", "Circle", "Dot"));
    private final NumberSetting size = addSetting(new NumberSetting("Size", "Crosshair size in pixels", 6.0, 2.0, 20.0, 1.0));
    private final NumberSetting gap = addSetting(new NumberSetting("Gap", "Gap from center", 2.0, 0.0, 10.0, 1.0));
    private final BooleanSetting dynamic = addSetting(new BooleanSetting("Dynamic", "Expand crosshair while moving", false));

    public Crosshair() {
        super("Crosshair", "Custom crosshair rendering", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        // Crosshair rendering is handled via HudRenderCallback or a mixin
        // on InGameHud.renderCrosshair. This tick handler can update
        // dynamic gap expansion based on player velocity.
        if (mc.player == null || !dynamic.getValue()) return;

        // Dynamic crosshair: gap increases with movement speed
        // Actual rendering uses getEffectiveGap() from a render callback
    }

    public String getStyle() {
        return style.getValue();
    }

    public int getSize() {
        return (int) size.getValue();
    }

    public double getEffectiveGap() {
        double base = gap.getValue();
        if (dynamic.getValue() && mc.player != null) {
            double speed = mc.player.getVelocity().horizontalLength();
            return base + speed * 10.0;
        }
        return base;
    }
}
