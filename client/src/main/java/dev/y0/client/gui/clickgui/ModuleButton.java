package dev.y0.client.gui.clickgui;

import dev.y0.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class ModuleButton {
    static final int HEIGHT = 16;

    private static final int BG_COLOR = 0xFF111111;
    private static final int HOVER_COLOR = 0xFF1C1C1C;
    private static final int ENABLED_COLOR = 0xFFE63946;
    private static final int DISABLED_COLOR = 0xFF888888;

    private final Module module;
    private final int offsetY;

    public ModuleButton(Module module, int offsetY) {
        this.module = module;
        this.offsetY = offsetY;
    }

    public void render(DrawContext context, int panelX, int panelContentY, int width, int mouseX, int mouseY) {
        int y = panelContentY + offsetY;
        boolean hovered = mouseX >= panelX && mouseX <= panelX + width
                && mouseY >= y && mouseY <= y + HEIGHT;

        context.fill(panelX, y, panelX + width, y + HEIGHT, hovered ? HOVER_COLOR : BG_COLOR);

        int textColor = module.isEnabled() ? ENABLED_COLOR : DISABLED_COLOR;
        context.drawTextWithShadow(
                MinecraftClient.getInstance().textRenderer,
                module.getName(),
                panelX + 4, y + 4,
                textColor
        );
    }

    public boolean mouseClicked(double mouseX, double mouseY, int panelX, int panelContentY, int width, int button) {
        int y = panelContentY + offsetY;
        if (mouseX >= panelX && mouseX <= panelX + width && mouseY >= y && mouseY <= y + HEIGHT) {
            if (button == 0) {
                module.toggle();
                return true;
            }
        }
        return false;
    }
}
