package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import net.minecraft.item.AxeItem;
import net.minecraft.item.SwordItem;

public class AutoWeapon extends Module {
    private final ModeSetting prefer = addSetting(new ModeSetting("Prefer", "Preferred weapon type", "Sword", "Sword", "Axe"));

    public AutoWeapon() {
        super("AutoWeapon", "Switches to best weapon in hotbar", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        int bestSlot = -1;
        double bestDamage = 0;

        for (int i = 0; i < 9; i++) {
            var item = mc.player.getInventory().getStack(i).getItem();
            boolean preferred = prefer.getValue().equals("Sword") ? item instanceof SwordItem : item instanceof AxeItem;
            boolean acceptable = item instanceof SwordItem || item instanceof AxeItem;

            if (preferred || acceptable) {
                double damage = item instanceof SwordItem s ? s.getMaterial().getAttackDamage()
                        : item instanceof AxeItem a ? a.getMaterial().getAttackDamage() : 0;
                double score = preferred ? damage + 100 : damage;
                if (score > bestDamage) {
                    bestDamage = score;
                    bestSlot = i;
                }
            }
        }

        if (bestSlot != -1) {
            mc.player.getInventory().selectedSlot = bestSlot;
        }
    }
}
