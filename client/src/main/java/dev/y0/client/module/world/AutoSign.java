package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;

public class AutoSign extends Module {
    private final BooleanSetting autoClose = addSetting(new BooleanSetting("AutoClose", "Close sign screen automatically", true));

    // Placeholder sign text lines - modify these as needed
    private final String[] signText = {"Y0 Client", "was here", "", ""};

    public AutoSign() {
        super("AutoSign", "Automatically fills signs with text", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        if (mc.currentScreen instanceof SignEditScreen) {
            // The sign edit screen is open; text would be applied here.
            // Requires access to the SignEditScreen's sign field via mixin or accessor
            // to call SignBlockEntity.setText() with the configured lines.
            if (autoClose.getValue()) {
                mc.player.closeHandledScreen();
            }
        }
    }
}
