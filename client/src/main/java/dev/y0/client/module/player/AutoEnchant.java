package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.screen.slot.SlotActionType;

public class AutoEnchant extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Ticks between enchant actions", 5.0, 1.0, 20.0, 1.0));

    private int tickCounter;

    public AutoEnchant() {
        super("AutoEnchant", "Automatically enchants items in enchanting table", Category.PLAYER);
    }

    @Override
    protected void onEnable() {
        tickCounter = 0;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.interactionManager == null) return;
        if (!(mc.player.currentScreenHandler instanceof EnchantmentScreenHandler handler)) return;

        tickCounter++;
        if (tickCounter < (int) delay.getValue()) return;
        tickCounter = 0;

        // Click the highest available enchantment (slot index 2 = level 3)
        if (handler.getLapisCount() > 0 && mc.player.experienceLevel >= 1) {
            mc.interactionManager.clickSlot(handler.syncId, 2, 0, SlotActionType.PICKUP, mc.player);
        }
    }
}
