package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.client.gui.screen.ingame.CraftingScreen;
import net.minecraft.screen.slot.SlotActionType;

public class AutoCraft extends Module {
    private final NumberSetting count = addSetting(new NumberSetting("Count", "Number of items to craft", 64.0, 1.0, 576.0, 1.0));
    private int crafted;

    public AutoCraft() {
        super("AutoCraft", "Automatically crafts items from the crafting table", Category.PLAYER);
    }

    @Override
    protected void onEnable() {
        crafted = 0;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.interactionManager == null) return;
        if (!(mc.currentScreen instanceof CraftingScreen screen)) return;
        if (crafted >= count.getValue()) return;

        if (!screen.getScreenHandler().getSlot(0).getStack().isEmpty()) {
            mc.interactionManager.clickSlot(screen.getScreenHandler().syncId, 0, 0, SlotActionType.QUICK_MOVE, mc.player);
            crafted++;
        }
    }
}
