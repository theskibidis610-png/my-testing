package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.item.SwordItem;

// Note: Full legacy sword blocking requires a mixin to override blocking behavior
public class SwordBlock extends Module {
    public SwordBlock() {
        super("SwordBlock", "Legacy sword blocking", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.getMainHandStack().getItem() instanceof SwordItem) {
            if (mc.options.useKey.isPressed()) {
                mc.player.setSneaking(true);
            }
        }
    }
}
