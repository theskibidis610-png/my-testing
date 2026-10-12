package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class ViewModel extends Module {
    private final NumberSetting x = addSetting(new NumberSetting("X", "X offset", 0.0, -2.0, 2.0, 0.1));
    private final NumberSetting y = addSetting(new NumberSetting("Y", "Y offset", 0.0, -2.0, 2.0, 0.1));
    private final NumberSetting z = addSetting(new NumberSetting("Z", "Z offset", 0.0, -2.0, 2.0, 0.1));

    public ViewModel() {
        super("ViewModel", "Custom view model position offsets", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // View model transform applied via HeldItemRenderer mixin
    }

    public double getX() { return x.getValue(); }
    public double getY() { return y.getValue(); }
    public double getZ() { return z.getValue(); }
}
