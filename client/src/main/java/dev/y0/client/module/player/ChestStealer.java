package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.screen.slot.SlotActionType;

public class ChestStealer extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Ticks between steals", 1.0, 0.0, 10.0, 1.0));
    private final BooleanSetting autoClose = addSetting(new BooleanSetting("Auto Close", "Close chest when done", true));

    private int tickCounter;

    public ChestStealer() {
        super("ChestStealer", "Quickly steal from chests", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.interactionManager == null) return;
        if (!(mc.currentScreen instanceof GenericContainerScreen container)) return;

        if (tickCounter < delay.getValue()) {
            tickCounter++;
            return;
        }
        tickCounter = 0;

        int containerSlots = container.getScreenHandler().getRows() * 9;
        boolean empty = true;

        for (int i = 0; i < containerSlots; i++) {
            if (!container.getScreenHandler().getSlot(i).getStack().isEmpty()) {
                mc.interactionManager.clickSlot(
                        container.getScreenHandler().syncId,
                        i, 0, SlotActionType.QUICK_MOVE, mc.player
                );
                empty = false;
                break;
            }
        }

        if (empty && autoClose.getValue()) {
            mc.player.closeHandledScreen();
        }
    }
}
