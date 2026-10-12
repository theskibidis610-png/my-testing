package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

public class BurstAttack extends Module {
    private final NumberSetting burstCount = addSetting(new NumberSetting("Burst Count", "Attacks per burst", 3.0, 1.0, 10.0, 1.0));

    public BurstAttack() {
        super("BurstAttack", "Quick burst attacks", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        for (PlayerEntity p : mc.world.getPlayers()) {
            if (p == mc.player) continue;
            if (mc.player.distanceTo(p) < 4.5) {
                for (int i = 0; i < (int) burstCount.getValue(); i++) {
                    mc.interactionManager.attackEntity(mc.player, p);
                    mc.player.swingHand(Hand.MAIN_HAND);
                }
                return;
            }
        }
    }
}
