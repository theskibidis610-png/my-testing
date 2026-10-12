package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.network.packet.c2s.play.CreativeInventoryActionC2SPacket;

public class InventoryDupe extends Module {

    public InventoryDupe() {
        super("InventoryDupe", "Attempt inventory dupe glitch", Category.MISC);
    }

    @Override
    protected void onEnable() {
        if (mc.player == null || mc.getNetworkHandler() == null) {
            Y0Client.LOGGER.warn("InventoryDupe: Player not available");
            setEnabled(false);
            return;
        }

        // One-shot: attempt to duplicate held item by sending
        // a creative inventory packet with the current held stack
        if (mc.player.getAbilities().creativeMode) {
            mc.getNetworkHandler().sendPacket(new CreativeInventoryActionC2SPacket(
                    36 + mc.player.getInventory().selectedSlot,
                    mc.player.getMainHandStack().copy()
            ));
            Y0Client.LOGGER.info("InventoryDupe: Sent duplicate packet");
        } else {
            Y0Client.LOGGER.warn("InventoryDupe: Requires creative mode");
        }

        // Disable after one-shot attempt
        setEnabled(false);
    }
}
