package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

import java.util.HashMap;
import java.util.Map;

public class PopCounter extends Module {
    private final BooleanSetting showInChat = addSetting(new BooleanSetting("Show In Chat", "Display pop count in chat", true));
    private final Map<String, Integer> popCounts = new HashMap<>();

    public PopCounter() {
        super("PopCounter", "Count totem pops", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        for (PlayerEntity p : mc.world.getPlayers()) {
            if (p == mc.player) continue;
            if (p.getHealth() <= 0) {
                int pops = popCounts.getOrDefault(p.getName().getString(), 0);
                if (showInChat.getValue() && pops > 0) {
                    mc.player.sendMessage(Text.literal(p.getName().getString() + " died after " + pops + " pops"), false);
                }
                popCounts.remove(p.getName().getString());
            }
        }
    }
}
