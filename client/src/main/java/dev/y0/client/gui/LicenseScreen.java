package dev.y0.client.gui;

import dev.y0.client.Y0Client;
import dev.y0.client.license.LicenseManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class LicenseScreen extends Screen {

    private TextFieldWidget keyField;
    private ButtonWidget activateButton;
    private String statusMessage = "";
    private int statusColor = 0xFFFFFF;
    private boolean processing;

    public LicenseScreen() {
        super(Text.literal("Y0 License"));
    }

    @Override
    protected void init() {
        int cx = width / 2;
        int cy = height / 2;

        keyField = new TextFieldWidget(textRenderer, cx - 120, cy - 10, 240, 20, Text.literal("License Key"));
        keyField.setMaxLength(19);
        keyField.setPlaceholder(Text.literal("Y0-XXXX-XXXX-XXXX"));
        addDrawableChild(keyField);

        activateButton = ButtonWidget.builder(Text.literal("Activate"), b -> activate())
                .dimensions(cx - 50, cy + 20, 100, 20)
                .build();
        addDrawableChild(activateButton);
    }

    private void activate() {
        String key = keyField.getText().trim().toUpperCase();
        if (key.isEmpty() || processing) return;

        processing = true;
        activateButton.active = false;
        statusMessage = "Validating...";
        statusColor = 0xFFFF00;

        new Thread(() -> {
            LicenseManager.ValidationResult result =
                    Y0Client.getInstance().getLicenseManager().activate(key);
            client.execute(() -> {
                processing = false;
                activateButton.active = true;
                if (result.success()) {
                    statusMessage = "License activated! Enjoy Y0.";
                    statusColor = 0x55FF55;
                    client.setScreen(null);
                } else {
                    statusMessage = result.message();
                    statusColor = 0xFF5555;
                }
            });
        }, "Y0-License").start();
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        ctx.fill(0, 0, width, height, 0xFF0a0a0a);

        int cx = width / 2;
        int cy = height / 2;

        ctx.drawCenteredTextWithShadow(textRenderer, "Y0", cx, cy - 70, 0xE63946);
        ctx.drawCenteredTextWithShadow(textRenderer, "Enter your license key", cx, cy - 50, 0x888888);

        super.render(ctx, mouseX, mouseY, delta);

        if (!statusMessage.isEmpty()) {
            ctx.drawCenteredTextWithShadow(textRenderer, statusMessage, cx, cy + 50, statusColor);
        }

        ctx.drawCenteredTextWithShadow(textRenderer, "Purchase at y0client.com", cx, height - 20, 0x555555);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public void close() {
        if (Y0Client.getInstance().getLicenseManager().isValid()) {
            super.close();
        }
    }
}
