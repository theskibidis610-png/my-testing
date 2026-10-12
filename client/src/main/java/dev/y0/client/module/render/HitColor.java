package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class HitColor extends Module {
    private final NumberSetting red = addSetting(new NumberSetting("Red", "Red channel", 255.0, 0.0, 255.0, 1.0));
    private final NumberSetting green = addSetting(new NumberSetting("Green", "Green channel", 0.0, 0.0, 255.0, 1.0));
    private final NumberSetting blue = addSetting(new NumberSetting("Blue", "Blue channel", 0.0, 0.0, 255.0, 1.0));

    public HitColor() {
        super("HitColor", "Custom flash color when entities are hit", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        // Hit color applied via entity render mixin on hurtTime
    }

    public int getColor() {
        return ((int) red.getValue() << 16) | ((int) green.getValue() << 8) | (int) blue.getValue();
    }
}
