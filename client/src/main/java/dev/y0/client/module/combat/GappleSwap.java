package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.Items;

public class GappleSwap extends Module {
    private final NumberSetting healthThreshold = addSetting(new NumberSetting("Health Threshold", "Health to swap at", 8.0, 1.0, 20.0, 0.5));

    public GappleSwap() {
        super("GappleSwap", "Swap to gapple mid-combat", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.getHealth() > healthThreshold.getValue()) return;
        for (int i = 0; i < 9; i++) {
            if (mc.player.getInventory().getStack(i).getItem() == Items.ENCHANTED_GOLDEN_APPLE) {
                mc.player.getInventory().selectedSlot = i;
                mc.options.useKey.setPressed(true);
                return;
            }
        }
    }
}
