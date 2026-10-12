package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class WindCharge extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Use delay in ticks", 10.0, 1.0, 40.0, 1.0));
    private int tickCounter;

    public WindCharge() {
        super("WindCharge", "Auto use wind charges", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        tickCounter++;
        if (tickCounter < delay.getValue()) return;
        tickCounter = 0;
        if (mc.player.getMainHandStack().getItem() == Items.WIND_CHARGE) {
            mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
        }
    }
}
