package dev.y0.client.module.player;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.screen.slot.SlotActionType;

public class HotbarSwap extends Module {

    public HotbarSwap() {
        super("HotbarSwap", "Swaps hotbar with the row above on enable", Category.PLAYER);
    }

    @Override
    protected void onEnable() {
        if (mc.player == null || mc.interactionManager == null) {
            disable();
            return;
        }

        for (int i = 0; i < 9; i++) {
            mc.interactionManager.clickSlot(
                    mc.player.currentScreenHandler.syncId,
                    9 + i, i, SlotActionType.SWAP, mc.player
            );
        }
        disable();
    }
}
