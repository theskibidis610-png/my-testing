package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class AntiPacketKick extends Module {

    public AntiPacketKick() {
        super("AntiPacketKick", "Prevent being kicked for large packets", Category.MISC);
    }

    // NOTE: This module requires a mixin on net.minecraft.network.ClientConnection
    // to intercept outgoing packets and split oversized ones.
    //
    // @Inject(method = "send(Lnet/minecraft/network/packet/Packet;)V", at = @At("HEAD"), cancellable = true)
    // The mixin should check the packet size and fragment or drop packets
    // that exceed the server's maximum allowed size (typically 32767 bytes).
    //
    // When this module is enabled, the mixin skips the disconnect logic
    // for oversized packets, allowing the client to remain connected.

    @Override
    protected void onEnable() {
        Y0Client.LOGGER.info("AntiPacketKick enabled - large packet kicks will be suppressed");
    }
}
