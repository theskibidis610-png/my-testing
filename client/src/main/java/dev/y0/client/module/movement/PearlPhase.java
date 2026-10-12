package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.item.Items;

public class PearlPhase extends Module {
    private final BooleanSetting auto = addSetting(new BooleanSetting("Auto", "Automatically use pearl when facing a wall", false));

    public PearlPhase() {
        super("PearlPhase", "Phase through blocks using ender pearls", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (!auto.getValue()) return;
        if (mc.player.horizontalCollision && mc.player.getMainHandStack().isOf(Items.ENDER_PEARL)) {
            mc.player.setPitch(-45);
        }
    }
}
