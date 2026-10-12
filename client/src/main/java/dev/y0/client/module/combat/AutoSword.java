package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.item.SwordItem;

public class AutoSword extends Module {

    public AutoSword() {
        super("AutoSword", "Switches to best sword before attacking", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.getMainHandStack().getItem() instanceof SwordItem) return;

        int bestSlot = -1;
        double bestDamage = 0;

        for (int i = 0; i < 9; i++) {
            if (mc.player.getInventory().getStack(i).getItem() instanceof SwordItem sword) {
                double damage = sword.getMaterial().getAttackDamage();
                if (damage > bestDamage) {
                    bestDamage = damage;
                    bestSlot = i;
                }
            }
        }

        if (bestSlot != -1) {
            mc.player.getInventory().selectedSlot = bestSlot;
        }
    }
}
