package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class EXPThrow extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Ticks between throws", 5.0, 0.0, 20.0, 1.0));
    private int tickCounter;

    public EXPThrow() {
        super("EXPThrow", "Automatically throws experience bottles", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.interactionManager == null) return;
        if (mc.player.getMainHandStack().getItem() != Items.EXPERIENCE_BOTTLE) return;

        tickCounter++;
        if (tickCounter >= delay.getValue()) {
            mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
            tickCounter = 0;
        }
    }
}
