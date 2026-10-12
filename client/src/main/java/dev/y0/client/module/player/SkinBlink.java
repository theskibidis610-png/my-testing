package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.client.render.entity.PlayerModelPart;

import java.util.Set;

public class SkinBlink extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Ticks between skin layer toggles", 10.0, 1.0, 100.0, 1.0));

    private int tickCounter;
    private boolean layersVisible = true;

    public SkinBlink() {
        super("SkinBlink", "Toggles skin layers on and off", Category.PLAYER);
    }

    @Override
    protected void onDisable() {
        // Re-enable all skin layers
        for (PlayerModelPart part : PlayerModelPart.values()) {
            mc.options.togglePlayerModelPart(part, true);
        }
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        tickCounter++;
        if (tickCounter >= (int) delay.getValue()) {
            tickCounter = 0;
            layersVisible = !layersVisible;

            for (PlayerModelPart part : PlayerModelPart.values()) {
                mc.options.togglePlayerModelPart(part, layersVisible);
            }
        }
    }
}
