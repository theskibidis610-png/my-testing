package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.AxeItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

public class Chainsaw extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Attack speed multiplier", 2.0, 1.0, 5.0, 0.5));
    private long lastAttack;

    public Chainsaw() {
        super("Chainsaw", "Fast axe attacks", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        if (!(mc.player.getMainHandStack().getItem() instanceof AxeItem)) return;
        long now = System.currentTimeMillis();
        if (now - lastAttack < (long) (1000.0 / speed.getValue())) return;
        for (PlayerEntity p : mc.world.getPlayers()) {
            if (p == mc.player) continue;
            if (mc.player.distanceTo(p) < 4.5) {
                mc.interactionManager.attackEntity(mc.player, p);
                mc.player.swingHand(Hand.MAIN_HAND);
                lastAttack = now;
                return;
            }
        }
    }
}
