package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import net.minecraft.item.Items;
import net.minecraft.item.Item;

public class Quiver extends Module {
    private final ModeSetting type = addSetting(new ModeSetting("Type", "Preferred arrow type", "Normal", "Spectral", "Poison", "Normal"));

    public Quiver() {
        super("Quiver", "Auto select best arrow", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        Item target = switch (type.getValue()) {
            case "Spectral" -> Items.SPECTRAL_ARROW;
            case "Poison" -> Items.TIPPED_ARROW;
            default -> Items.ARROW;
        };
        for (int i = 0; i < 9; i++) {
            if (mc.player.getInventory().getStack(i).getItem() == target) {
                mc.player.getInventory().selectedSlot = i;
                return;
            }
        }
    }
}
