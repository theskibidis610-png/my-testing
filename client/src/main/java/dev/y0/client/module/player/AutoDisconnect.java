package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;

public class AutoDisconnect extends Module {
    private final NumberSetting healthThreshold = addSetting(new NumberSetting("Health", "Disconnect below this health", 6.0, 1.0, 19.0, 1.0));

    public AutoDisconnect() {
        super("AutoDisconnect", "Disconnect when health is low", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        // Skip check if player is dead to avoid triggering on respawn
        if (mc.player.isDead()) return;

        float health = mc.player.getHealth();

        if (health > 0 && health <= healthThreshold.getValue()) {
            // Disconnect from server
            if (mc.player.networkHandler != null) {
                mc.player.networkHandler.getConnection().disconnect(Text.literal("AutoDisconnect: Health too low (" + String.format("%.1f", health) + ")"));
            }
            // Disable to prevent repeated disconnects on reconnect
            this.disable();
        }
    }
}
