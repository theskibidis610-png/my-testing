package dev.y0.client.module.misc;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class Unfocused extends Module {

    public Unfocused() {
        super("Unfocused", "Continue processing when window is unfocused", Category.MISC);
    }

    // NOTE: This module requires a mixin on net.minecraft.client.MinecraftClient
    // to override the onWindowFocusChanged callback and prevent the client
    // from reducing its tick rate when the window loses focus.
    //
    // @Inject(method = "onWindowFocusChanged", at = @At("HEAD"), cancellable = true)
    // When this module is enabled, the mixin cancels the focus-lost handler,
    // keeping the game running at full speed in the background.
    //
    // Additionally, a mixin on the render loop should prevent the FPS limiter
    // from dropping to the unfocused framerate (usually 1 FPS).
}
