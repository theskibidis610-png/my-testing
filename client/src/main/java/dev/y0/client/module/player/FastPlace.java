package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class FastPlace extends Module {

    public FastPlace() {
        super("FastPlace", "Remove block placement delay", Category.PLAYER);
    }

    // Requires a mixin on MinecraftClient.itemUseCooldown to set it to 0
    // The module toggle and framework are ready.
}
