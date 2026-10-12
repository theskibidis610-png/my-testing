package dev.y0.client.module.player;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class VanillaSpoof extends Module {

    public VanillaSpoof() {
        // Companion mixin intercepts brand payload in ClientPlayNetworkHandler
        super("VanillaSpoof", "Spoofs the client brand as vanilla", Category.PLAYER);
    }

    // Requires mixin on ClientPlayNetworkHandler to intercept
    // the brand payload and send "vanilla" instead of the modded brand.
}
