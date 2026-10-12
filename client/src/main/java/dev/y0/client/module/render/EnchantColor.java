package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class EnchantColor extends Module {
    private final NumberSetting red = addSetting(new NumberSetting("Red", "Red channel", 128.0, 0.0, 255.0, 1.0));
    private final NumberSetting green = addSetting(new NumberSetting("Green", "Green channel", 0.0, 0.0, 255.0, 1.0));
    private final NumberSetting blue = addSetting(new NumberSetting("Blue", "Blue channel", 255.0, 0.0, 255.0, 1.0));

    public EnchantColor() {
        super("EnchantColor", "Custom enchantment glint color", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        // Glint color applied via item render mixin
    }

    public int getColor() {
        return ((int) red.getValue() << 16) | ((int) green.getValue() << 8) | (int) blue.getValue();
    }
}
