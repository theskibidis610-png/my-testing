package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

import java.util.HashSet;
import java.util.Set;

public class VisualRange extends Module {
    private final BooleanSetting chatAlert = addSetting(new BooleanSetting("Chat Alert", "Show alert in chat", true));
    private final Set<String> knownPlayers = new HashSet<>();

    public VisualRange() {
        super("VisualRange", "Alert players in visual range", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        Set<String> current = new HashSet<>();
        for (PlayerEntity p : mc.world.getPlayers()) {
            if (p == mc.player) continue;
            String name = p.getName().getString();
            current.add(name);
            if (!knownPlayers.contains(name) && chatAlert.getValue()) {
                mc.player.sendMessage(Text.literal("[Y0] " + name + " entered visual range"), false);
            }
        }
        for (String name : knownPlayers) {
            if (!current.contains(name) && chatAlert.getValue()) {
                mc.player.sendMessage(Text.literal("[Y0] " + name + " left visual range"), false);
            }
        }
        knownPlayers.clear();
        knownPlayers.addAll(current);
    }
}
