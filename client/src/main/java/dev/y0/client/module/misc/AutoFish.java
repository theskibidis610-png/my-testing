package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class AutoFish extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Recast Delay", "Ticks to wait before recasting", 10.0, 1.0, 40.0, 1.0));

    private int waitTicks;
    private boolean caught;

    public AutoFish() {
        super("AutoFish", "Automatically fish", Category.MISC);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.interactionManager == null) return;
        if (mc.player.getMainHandStack().getItem() != Items.FISHING_ROD) return;

        if (mc.player.fishHook != null) {
            if (mc.player.fishHook.isInOpenWater()) {
                // Check if bobber dipped (velocity y < -0.01 indicates a bite)
                if (mc.player.fishHook.getVelocity().y < -0.05) {
                    mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
                    caught = true;
                    waitTicks = 0;
                }
            }
        } else if (caught) {
            waitTicks++;
            if (waitTicks >= delay.getValue()) {
                mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
                caught = false;
                waitTicks = 0;
            }
        }
    }
}
