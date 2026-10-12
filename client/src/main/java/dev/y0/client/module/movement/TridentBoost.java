package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.Items;

public class TridentBoost extends Module {
    private final NumberSetting boost = addSetting(new NumberSetting("Boost", "Trident boost multiplier", 2.0, 1.0, 5.0, 0.5));

    public TridentBoost() {
        super("TridentBoost", "Boost trident riptide speed", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.isUsingRiptide() && mc.player.getMainHandStack().getItem() == Items.TRIDENT) {
            mc.player.setVelocity(mc.player.getVelocity().multiply(boost.getValue(), 1.0, boost.getValue()));
        }
    }
}
