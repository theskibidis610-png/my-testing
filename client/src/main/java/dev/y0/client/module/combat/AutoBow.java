package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.BowItem;

public class AutoBow extends Module {
    private final NumberSetting chargeTicks = addSetting(new NumberSetting("Charge Ticks", "Ticks before auto release", 20.0, 3.0, 20.0, 1.0));

    public AutoBow() {
        super("AutoBow", "Automatically releases bow at full charge", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (!mc.player.isUsingItem()) return;
        if (!(mc.player.getActiveItem().getItem() instanceof BowItem)) return;

        int useTime = mc.player.getItemUseTime();
        if (useTime >= (int) chargeTicks.getValue()) {
            mc.interactionManager.stopUsingItem(mc.player);
        }
    }
}
