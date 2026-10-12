package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import net.minecraft.item.SwordItem;
import net.minecraft.item.MiningToolItem;

public class AutoSwitch extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "When to switch items", "Both", "Mining", "Combat", "Both"));

    public AutoSwitch() {
        super("AutoSwitch", "Automatically switch to the best item for the context", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.interactionManager == null) return;

        boolean mining = mode.getValue().equals("Mining") || mode.getValue().equals("Both");
        boolean combat = mode.getValue().equals("Combat") || mode.getValue().equals("Both");

        if (combat && mc.player.getAttacking() != null) {
            for (int i = 0; i < 9; i++) {
                if (mc.player.getInventory().getStack(i).getItem() instanceof SwordItem) {
                    mc.player.getInventory().selectedSlot = i;
                    break;
                }
            }
        }
    }
}
