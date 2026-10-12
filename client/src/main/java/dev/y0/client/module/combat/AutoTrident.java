package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class AutoTrident extends Module {
    private final NumberSetting chargeTime = addSetting(new NumberSetting("Charge Time", "Ticks to charge before throwing", 15.0, 5.0, 40.0, 1.0));
    private int chargeTicks;

    public AutoTrident() {
        super("AutoTrident", "Auto throw trident", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.getMainHandStack().getItem() != Items.TRIDENT) return;
        if (mc.player.getAttacking() != null) {
            mc.options.useKey.setPressed(true);
            chargeTicks++;
            if (chargeTicks >= chargeTime.getValue()) {
                mc.options.useKey.setPressed(false);
                chargeTicks = 0;
            }
        }
    }
}
