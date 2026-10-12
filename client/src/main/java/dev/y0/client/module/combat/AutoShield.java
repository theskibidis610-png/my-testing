package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.Hand;

public class AutoShield extends Module {
    private final NumberSetting reactionDelay = addSetting(new NumberSetting("Reaction Delay", "Ticks delay before raising shield", 0.0, 0.0, 10.0, 1.0));

    private int hurtTimer;

    public AutoShield() {
        super("AutoShield", "Automatically raises shield when taking damage", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        boolean hasShield = mc.player.getOffHandStack().getItem() instanceof ShieldItem
                || mc.player.getMainHandStack().getItem() instanceof ShieldItem;
        if (!hasShield) return;

        if (mc.player.hurtTime > 0) {
            hurtTimer = 0;
        }

        if (mc.player.hurtTime > 0 || hurtTimer < 20) {
            hurtTimer++;
            if (hurtTimer >= (int) reactionDelay.getValue() && !mc.player.isBlocking()) {
                Hand hand = mc.player.getOffHandStack().getItem() instanceof ShieldItem ? Hand.OFF_HAND : Hand.MAIN_HAND;
                mc.interactionManager.interactItem(mc.player, hand);
            }
        }
    }
}
