package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class PearlTP extends Module {
    private final BooleanSetting auto = addSetting(new BooleanSetting("Auto", "Automatically throw pearl at crosshair", false));

    public PearlTP() {
        super("PearlTP", "Quickly throws ender pearls to teleport", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.interactionManager == null) return;
        if (!auto.getValue()) return;

        if (mc.player.getMainHandStack().getItem() == Items.ENDER_PEARL) {
            mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
            disable();
        }
    }
}
