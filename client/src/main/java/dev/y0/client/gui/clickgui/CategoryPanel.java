package dev.y0.client.gui.clickgui;

import dev.y0.client.Y0Client;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

public class CategoryPanel {
    private final Category category;
    private int x, y;
    private final int width;
    private boolean dragging;
    private double dragOffsetX, dragOffsetY;
    private boolean collapsed;
    private final List<ModuleButton> buttons = new ArrayList<>();

    private static final int HEADER_HEIGHT = 20;
    private static final int HEADER_COLOR = 0xFF1A1A1A;
    private static final int HEADER_TEXT_COLOR = 0xFFE63946;
    private static final int BG_COLOR = 0xFF111111;

    public CategoryPanel(Category category, int x, int y, int width) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = width;

        int buttonY = 0;
        for (Module module : Y0Client.getInstance().getModuleManager().getByCategory(category)) {
            buttons.add(new ModuleButton(module, buttonY));
            buttonY += ModuleButton.HEIGHT;
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY) {
        context.fill(x, y, x + width, y + HEADER_HEIGHT, HEADER_COLOR);
        context.drawCenteredTextWithShadow(
                net.minecraft.client.MinecraftClient.getInstance().textRenderer,
                category.getDisplayName(),
                x + width / 2, y + 6,
                HEADER_TEXT_COLOR
        );

        if (collapsed) return;

        int contentY = y + HEADER_HEIGHT;
        for (ModuleButton button : buttons) {
            button.render(context, x, contentY, width, mouseX, mouseY);
            contentY += ModuleButton.HEIGHT;
        }

        context.fill(x, contentY, x + width, contentY + 2, HEADER_COLOR);
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + HEADER_HEIGHT) {
            if (button == 1) {
                collapsed = !collapsed;
                return true;
            }
            dragging = true;
            dragOffsetX = mouseX - x;
            dragOffsetY = mouseY - y;
            return true;
        }

        if (collapsed) return false;

        int contentY = y + HEADER_HEIGHT;
        for (ModuleButton moduleButton : buttons) {
            if (moduleButton.mouseClicked(mouseX, mouseY, x, contentY, width, button)) {
                return true;
            }
            contentY += ModuleButton.HEIGHT;
        }

        return false;
    }

    public void mouseReleased() {
        dragging = false;
    }

    public boolean mouseDragged(double mouseX, double mouseY) {
        if (dragging) {
            x = (int) (mouseX - dragOffsetX);
            y = (int) (mouseY - dragOffsetY);
            return true;
        }
        return false;
    }
}
