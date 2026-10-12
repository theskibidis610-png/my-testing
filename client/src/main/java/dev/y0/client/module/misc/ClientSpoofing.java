package dev.y0.client.module.misc;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;

public class ClientSpoofing extends Module {
    private final ModeSetting brand = addSetting(new ModeSetting("Brand", "Client brand to report to server", "Vanilla", "Vanilla", "Forge", "Optifine"));

    public ClientSpoofing() {
        super("ClientSpoofing", "Spoof the client brand sent to the server", Category.MISC);
    }

    // NOTE: Requires a mixin on ClientPlayNetworkHandler to intercept
    // the brand custom payload packet and replace it with the spoofed value.

    public String getSpoofedBrand() {
        return brand.getValue().toLowerCase();
    }
}
