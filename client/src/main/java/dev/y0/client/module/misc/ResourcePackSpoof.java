package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class ResourcePackSpoof extends Module {
    private final BooleanSetting autoAccept = addSetting(new BooleanSetting("Auto Accept", "Auto-accept server resource packs without downloading", true));

    public ResourcePackSpoof() {
        super("ResourcePackSpoof", "Spoof resource pack responses to the server", Category.MISC);
    }

    @Override
    protected void onEnable() {
        // NOTE: Requires a mixin on ClientPlayNetworkHandler to intercept
        // ResourcePackSendS2CPacket and respond with ACCEPTED/LOADED
        // without actually downloading the pack.
        Y0Client.LOGGER.info("ResourcePackSpoof enabled - auto accept: {}", autoAccept.getValue());
    }

    public boolean shouldAutoAccept() {
        return autoAccept.getValue();
    }
}
