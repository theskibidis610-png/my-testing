package dev.y0.client.module.world;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class PacketCancel extends Module {
    private final BooleanSetting cancelMovement = addSetting(new BooleanSetting("Movement", "Cancel movement packets", false));
    private final BooleanSetting cancelSwing = addSetting(new BooleanSetting("Swing", "Cancel hand swing packets", false));
    private final BooleanSetting cancelInteract = addSetting(new BooleanSetting("Interact", "Cancel interact packets", false));

    public PacketCancel() {
        super("PacketCancel", "Cancel specific outgoing packets", Category.WORLD);
    }

    // Requires a mixin on ClientConnection.send() to intercept outgoing packets.
    // In the mixin, check if this module is enabled and then:
    // - If cancelMovement is true, cancel PlayerMoveC2SPacket instances
    // - If cancelSwing is true, cancel HandSwingC2SPacket instances
    // - If cancelInteract is true, cancel PlayerInteractEntityC2SPacket instances

    public boolean shouldCancelMovement() {
        return isEnabled() && cancelMovement.getValue();
    }

    public boolean shouldCancelSwing() {
        return isEnabled() && cancelSwing.getValue();
    }

    public boolean shouldCancelInteract() {
        return isEnabled() && cancelInteract.getValue();
    }
}
