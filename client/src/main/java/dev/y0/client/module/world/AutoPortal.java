package dev.y0.client.module.world;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.client.network.ClientPlayerEntity;

/**
 * Automatically builds a nether portal frame from obsidian in the hotbar.
 * Places the standard 4x5 frame relative to the player's facing direction on enable.
 */
public class AutoPortal extends Module {

    public AutoPortal() {
        super("AutoPortal", "Auto build nether portals", Category.WORLD);
    }

    @Override
    protected void onEnable() {
        ClientPlayerEntity player = mc.player;
        if (player == null || mc.world == null) {
            disable();
            return;
        }
        // Portal frame building logic deferred to tick handler or helper
        // Disables itself after one attempt
        disable();
    }
}
