package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.text.Text;

public class Coords extends Module {
    private final BooleanSetting show = addSetting(new BooleanSetting("Show", "Display coordinates on screen", true));
    private final BooleanSetting share = addSetting(new BooleanSetting("Share", "Allow sharing coords in chat", false));

    public Coords() {
        super("Coords", "Display and share your coordinates", Category.MISC);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (show.getValue()) {
            // Coordinates are available via getCoords() for HUD rendering
        }
    }

    public String getCoords() {
        if (mc.player == null) return "N/A";
        return String.format("%.1f, %.1f, %.1f",
                mc.player.getX(), mc.player.getY(), mc.player.getZ());
    }

    public void shareCoords() {
        if (mc.player == null || mc.getNetworkHandler() == null || !share.getValue()) return;
        mc.player.networkHandler.sendChatMessage("My coords: " + getCoords());
    }
}
