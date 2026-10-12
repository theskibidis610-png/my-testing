package dev.y0.client.module.render;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class SkyColor extends Module {
    private final NumberSetting red = addSetting(new NumberSetting("Red", "Red channel", 128.0, 0.0, 255.0, 1.0));
    private final NumberSetting green = addSetting(new NumberSetting("Green", "Green channel", 180.0, 0.0, 255.0, 1.0));
    private final NumberSetting blue = addSetting(new NumberSetting("Blue", "Blue channel", 255.0, 0.0, 255.0, 1.0));

    public SkyColor() {
        super("SkyColor", "Custom sky color", Category.RENDER);
    }

    public int getRed() {
        return (int) red.getValue();
    }

    public int getGreen() {
        return (int) green.getValue();
    }

    public int getBlue() {
        return (int) blue.getValue();
    }

    public int getColorRGB() {
        return (getRed() << 16) | (getGreen() << 8) | getBlue();
    }
}
