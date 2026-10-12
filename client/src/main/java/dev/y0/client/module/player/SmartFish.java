package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class SmartFish extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Ticks before recasting", 15.0, 5.0, 60.0, 1.0));
    private int tickCounter;

    public SmartFish() {
        super("SmartFish", "Automatically reels in and recasts fishing rod", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.interactionManager == null) return;
        if (mc.player.getMainHandStack().getItem() != Items.FISHING_ROD) return;

        if (mc.player.fishHook != null && mc.player.fishHook.getVelocity().y < -0.04) {
            mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
            tickCounter = 0;
        } else if (mc.player.fishHook == null && ++tickCounter >= delay.getValue()) {
            mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
            tickCounter = 0;
        }
    }
}
