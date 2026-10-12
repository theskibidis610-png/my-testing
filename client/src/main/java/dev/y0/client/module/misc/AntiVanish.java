package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.client.network.PlayerListEntry;

import java.util.HashSet;
import java.util.Set;

public class AntiVanish extends Module {
    private final Set<String> previousPlayers = new HashSet<>();

    public AntiVanish() {
        super("AntiVanish", "Detect vanished players via tab list changes", Category.MISC);
    }

    @Override
    protected void onEnable() {
        previousPlayers.clear();
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.getNetworkHandler() == null) return;

        Set<String> currentPlayers = new HashSet<>();
        for (PlayerListEntry entry : mc.getNetworkHandler().getPlayerList()) {
            currentPlayers.add(entry.getProfile().getName());
        }

        for (String name : previousPlayers) {
            if (!currentPlayers.contains(name)) {
                mc.player.sendMessage(net.minecraft.text.Text.literal(
                        "[AntiVanish] " + name + " may have vanished"), false);
            }
        }

        for (String name : currentPlayers) {
            if (!previousPlayers.isEmpty() && !previousPlayers.contains(name)) {
                mc.player.sendMessage(net.minecraft.text.Text.literal(
                        "[AntiVanish] " + name + " has appeared"), false);
            }
        }

        previousPlayers.clear();
        previousPlayers.addAll(currentPlayers);
    }
}
