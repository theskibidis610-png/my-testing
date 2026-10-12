package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

public class FightBot extends Module {
    private final ModeSetting aggression = addSetting(new ModeSetting("Aggression", "Fighting aggression level", "Normal", "Passive", "Normal", "Aggressive"));

    public FightBot() {
        super("FightBot", "Automated fighting AI", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        double range = aggression.getValue().equals("Aggressive") ? 6.0 : aggression.getValue().equals("Normal") ? 4.5 : 3.0;
        for (PlayerEntity p : mc.world.getPlayers()) {
            if (p == mc.player) continue;
            if (mc.player.distanceTo(p) < range) {
                if (!aggression.getValue().equals("Passive") || p.getAttacking() == mc.player) {
                    mc.interactionManager.attackEntity(mc.player, p);
                    mc.player.swingHand(Hand.MAIN_HAND);
                }
                return;
            }
        }
    }
}
