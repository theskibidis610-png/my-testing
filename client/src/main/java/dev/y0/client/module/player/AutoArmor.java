package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.SlotActionType;

public class AutoArmor extends Module {
    private int tickDelay;

    public AutoArmor() {
        super("AutoArmor", "Auto equip best armor", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.interactionManager == null) return;
        if (mc.currentScreen != null) return;

        if (tickDelay > 0) {
            tickDelay--;
            return;
        }

        for (int armorSlot = 0; armorSlot < 4; armorSlot++) {
            ItemStack current = mc.player.getInventory().getArmorStack(armorSlot);
            int bestSlot = -1;
            int bestProtection = current.getItem() instanceof ArmorItem a ? a.getProtection() : 0;

            for (int i = 0; i < 36; i++) {
                ItemStack stack = mc.player.getInventory().getStack(i);
                if (!(stack.getItem() instanceof ArmorItem armor)) continue;
                if (armor.getSlotType().ordinal() != armorSlot) continue;
                if (armor.getProtection() > bestProtection) {
                    bestProtection = armor.getProtection();
                    bestSlot = i;
                }
            }

            if (bestSlot != -1) {
                int invSlot = bestSlot < 9 ? bestSlot + 36 : bestSlot;
                int armorInvSlot = 8 - armorSlot;

                if (!current.isEmpty()) {
                    mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, armorInvSlot, 0, SlotActionType.QUICK_MOVE, mc.player);
                }
                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, invSlot, 0, SlotActionType.QUICK_MOVE, mc.player);

                tickDelay = 2;
                return;
            }
        }
    }
}
