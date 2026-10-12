package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.Hand;

public class AutoClicker extends Module {
    private final NumberSetting cps = addSetting(new NumberSetting("CPS", "Clicks per second", 10.0, 1.0, 20.0, 1.0));
    private final BooleanSetting rightClick = addSetting(new BooleanSetting("Right Click", "Also auto right-click", false));
    private long lastClick;

    public AutoClicker() {
        super("AutoClicker", "Automatically clicks", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.currentScreen != null) return;
        long now = System.currentTimeMillis();
        if (now - lastClick < 1000.0 / cps.getValue()) return;
        if (mc.options.attackKey.isPressed()) {
            mc.player.swingHand(Hand.MAIN_HAND);
            lastClick = now;
        }
    }
}
