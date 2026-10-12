package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.BowItem;
import net.minecraft.util.Hand;

public class BowSpam extends Module {
    private final NumberSetting pullTicks = addSetting(new NumberSetting("Pull Ticks", "Ticks to pull bow before releasing", 3.0, 3.0, 10.0, 1.0));

    private int ticks;

    public BowSpam() {
        super("BowSpam", "Rapidly shoots bow with minimal charge", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (!(mc.player.getMainHandStack().getItem() instanceof BowItem)) return;

        if (mc.player.isUsingItem()) {
            if (mc.player.getItemUseTime() >= (int) pullTicks.getValue()) {
                mc.interactionManager.stopUsingItem(mc.player);
                ticks = 0;
            }
        } else {
            ticks++;
            if (ticks >= 2) {
                mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
            }
        }
    }
}
