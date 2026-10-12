package dev.y0.client.module.player;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class GodMode extends Module {

    public GodMode() {
        super("GodMode", "Client-side god mode attempt", Category.PLAYER);
    }

    // Requires mixin on ClientPlayerEntity.damage to cancel incoming damage
    // when this module is enabled. Only works client-side; server may reject.
}
