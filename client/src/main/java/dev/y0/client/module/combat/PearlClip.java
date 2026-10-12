package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class PearlClip extends Module {
    private final BooleanSetting auto = addSetting(new BooleanSetting("Auto", "Automatically pearl clip when near walls", false));

    public PearlClip() {
        super("PearlClip", "Pearl clip through blocks", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (!auto.getValue()) return;
        if (mc.player.horizontalCollision && mc.player.getMainHandStack().getItem() == Items.ENDER_PEARL) {
            mc.player.setPitch(90);
            mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
        }
    }
}
