package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.block.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;

public class NoInteract extends Module {
    private final BooleanSetting noCrafting = addSetting(new BooleanSetting("NoCrafting", "Block crafting table interaction", false));
    private final BooleanSetting noChests = addSetting(new BooleanSetting("NoChests", "Block chest interaction", false));
    private final BooleanSetting noBeds = addSetting(new BooleanSetting("NoBeds", "Block bed interaction", false));

    public NoInteract() {
        super("NoInteract", "Prevent interaction with certain block types", Category.WORLD);
    }

    public boolean shouldCancel(Block block) {
        if (!isEnabled()) return false;
        if (noCrafting.getValue() && block instanceof CraftingTableBlock) return true;
        if (noChests.getValue() && block instanceof ChestBlock) return true;
        if (noBeds.getValue() && block instanceof BedBlock) return true;
        return false;
    }

    // This module is queried by a mixin into ClientPlayerInteractionManager#interactBlock
    // to cancel interactions with filtered block types.
}
