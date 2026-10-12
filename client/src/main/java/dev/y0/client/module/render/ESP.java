package dev.y0.client.module.render;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import dev.y0.client.setting.ModeSetting;
import dev.y0.client.setting.NumberSetting;
import org.lwjgl.glfw.GLFW;

public class ESP extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "ESP render mode", "Box", "Box", "Outline", "Glow"));
    private final BooleanSetting players = addSetting(new BooleanSetting("Players", "Show players", true));
    private final BooleanSetting mobs = addSetting(new BooleanSetting("Mobs", "Show mobs", false));
    private final BooleanSetting items = addSetting(new BooleanSetting("Items", "Show items", false));
    private final NumberSetting lineWidth = addSetting(new NumberSetting("Line Width", "ESP line thickness", 2.0, 0.5, 5.0, 0.5));

    public ESP() {
        super("ESP", "See entities through walls", Category.RENDER, GLFW.GLFW_KEY_H);
    }

    // Rendering logic requires a WorldRenderEvent mixin (RenderWorldLastEvent).
    // Implement the actual drawing in a render mixin once you add one.
    // The settings and toggle framework are ready.
}
