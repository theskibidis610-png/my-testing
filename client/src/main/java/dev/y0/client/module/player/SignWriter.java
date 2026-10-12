package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;

public class SignWriter extends Module {

    public SignWriter() {
        super("SignWriter", "Automatically writes on signs and closes the screen", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (mc.currentScreen instanceof SignEditScreen) {
            mc.currentScreen.close();
        }
    }
}
