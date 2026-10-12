package dev.y0.client.module.render;

import dev.y0.client.Y0Client;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

import java.util.List;

public class HUD extends Module {
    private final BooleanSetting watermark = addSetting(new BooleanSetting("Watermark", "Show Y0 watermark", true));
    private final BooleanSetting arraylist = addSetting(new BooleanSetting("ArrayList", "Show enabled modules", true));
    private final BooleanSetting coordinates = addSetting(new BooleanSetting("Coords", "Show coordinates", true));
    private final BooleanSetting fps = addSetting(new BooleanSetting("FPS", "Show FPS counter", true));

    public HUD() {
        super("HUD", "On-screen overlay", Category.RENDER);

        HudRenderCallback.EVENT.register((DrawContext context, RenderTickCounter tickCounter) -> {
            if (!isEnabled() || mc.player == null) return;
            render(context);
        });
    }

    private void render(DrawContext context) {
        int screenWidth = mc.getWindow().getScaledWidth();
        int y = 4;

        if (watermark.getValue()) {
            context.drawTextWithShadow(mc.textRenderer, "Y0 v" + Y0Client.VERSION, 4, y, 0xFFE63946);
            y += 12;
        }

        if (fps.getValue()) {
            context.drawTextWithShadow(mc.textRenderer, mc.getCurrentFps() + " FPS", 4, y, 0xFFAAAAAA);
            y += 12;
        }

        if (coordinates.getValue() && mc.player != null) {
            String coords = String.format("%.1f, %.1f, %.1f", mc.player.getX(), mc.player.getY(), mc.player.getZ());
            context.drawTextWithShadow(mc.textRenderer, coords, 4, y, 0xFFAAAAAA);
        }

        if (arraylist.getValue()) {
            List<Module> enabled = Y0Client.getInstance().getModuleManager().getModules().stream()
                    .filter(Module::isEnabled)
                    .sorted((a, b) -> mc.textRenderer.getWidth(b.getName()) - mc.textRenderer.getWidth(a.getName()))
                    .toList();

            int ay = 4;
            for (Module module : enabled) {
                int width = mc.textRenderer.getWidth(module.getName());
                context.drawTextWithShadow(mc.textRenderer, module.getName(), screenWidth - width - 4, ay, 0xFFE63946);
                ay += 12;
            }
        }
    }

    @Override
    protected void onEnable() {
        // HUD renders via callback, no event bus needed
    }
}
