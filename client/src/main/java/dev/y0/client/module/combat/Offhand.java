package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.screen.slot.SlotActionType;

public class Offhand extends Module {
    private final ModeSetting item = addSetting(new ModeSetting("Item", "Item to hold in offhand", "Totem", "Totem", "Crystal", "Shield", "Gapple"));

    public Offhand() {
        super("Offhand", "Automatically manages offhand item", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        Item desired = switch (item.getValue()) {
            case "Crystal" -> Items.END_CRYSTAL;
            case "Shield" -> Items.SHIELD;
            case "Gapple" -> Items.ENCHANTED_GOLDEN_APPLE;
            default -> Items.TOTEM_OF_UNDYING;
        };

        if (mc.player.getOffHandStack().getItem() == desired) return;

        for (int i = 0; i < 36; i++) {
            if (mc.player.getInventory().getStack(i).getItem() == desired) {
                int slot = i < 9 ? i + 36 : i;
                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, slot, 40, SlotActionType.SWAP, mc.player);
                break;
            }
        }
    }
}
