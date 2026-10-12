package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import org.lwjgl.glfw.GLFW;

public class Fullbright extends Module {
    private double previousGamma;

    public Fullbright() {
        super("Fullbright", "See in the dark", Category.RENDER, GLFW.GLFW_KEY_G);
    }

    @Override
    protected void onEnable() {
        if (mc.options != null) {
            previousGamma = mc.options.getGamma().getValue();
            mc.options.getGamma().setValue(16.0);
        }
    }

    @Override
    protected void onDisable() {
        if (mc.options != null) {
            mc.options.getGamma().setValue(previousGamma);
        }
    }
}
