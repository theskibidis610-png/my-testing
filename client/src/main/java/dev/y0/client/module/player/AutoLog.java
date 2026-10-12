package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.network.packet.s2c.common.DisconnectS2CPacket;
import net.minecraft.text.Text;

public class AutoLog extends Module {
    private final NumberSetting healthThreshold = addSetting(new NumberSetting("Health", "Log out below this health", 4.0, 1.0, 20.0, 1.0));
    private final BooleanSetting onTotemPop = addSetting(new BooleanSetting("On Totem Pop", "Log out when totem pops", false));

    private float lastHealth;

    public AutoLog() {
        super("AutoLog", "Automatically disconnects on dangerous events", Category.PLAYER);
    }

    @Override
    protected void onEnable() {
        if (mc.player != null) lastHealth = mc.player.getHealth();
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.getNetworkHandler() == null) return;

        float health = mc.player.getHealth();

        if (health <= healthThreshold.getValue() && health > 0) {
            disconnect("Health too low (" + String.format("%.1f", health) + ")");
            return;
        }

        // Detect totem pop: health was 0 or near 0 and jumped back
        if (onTotemPop.getValue() && lastHealth <= 1.0f && health > lastHealth + 5.0f) {
            disconnect("Totem popped");
            return;
        }

        lastHealth = health;
    }

    private void disconnect(String reason) {
        mc.player.networkHandler.onDisconnect(new DisconnectS2CPacket(Text.literal("[AutoLog] " + reason)));
        disable();
    }
}
