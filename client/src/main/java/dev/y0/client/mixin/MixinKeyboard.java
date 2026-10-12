package dev.y0.client.mixin;

import dev.y0.client.Y0Client;
import dev.y0.client.event.events.EventKeyPress;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class MixinKeyboard {

    @Inject(method = "onKey", at = @At("HEAD"))
    private void onKey(long window, int key, int scanCode, int action, int modifiers, CallbackInfo ci) {
        if (action == 1) {
            Y0Client.getInstance().getEventBus().post(new EventKeyPress(key, scanCode));
        }
    }
}
