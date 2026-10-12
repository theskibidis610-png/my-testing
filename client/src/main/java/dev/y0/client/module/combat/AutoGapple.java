package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class AutoGapple extends Module {
    private final NumberSetting healthThreshold = addSetting(new NumberSetting("Health", "Health threshold to eat gapple", 8.0, 1.0, 20.0, 0.5));

    public AutoGapple() {
        super("AutoGapple", "Auto eats golden apple at low health", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.getHealth() > healthThreshold.getValue()) return;
        if (mc.player.isUsingItem()) return;

        Hand hand = null;
        if (mc.player.getMainHandStack().getItem() == Items.ENCHANTED_GOLDEN_APPLE
                || mc.player.getMainHandStack().getItem() == Items.GOLDEN_APPLE) {
            hand = Hand.MAIN_HAND;
        } else if (mc.player.getOffHandStack().getItem() == Items.ENCHANTED_GOLDEN_APPLE
                || mc.player.getOffHandStack().getItem() == Items.GOLDEN_APPLE) {
            hand = Hand.OFF_HAND;
        }

        if (hand != null) {
            mc.interactionManager.interactItem(mc.player, hand);
        }
    }
}
