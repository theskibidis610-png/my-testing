package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

public class TriggerBot extends Module {
    private final NumberSetting cps = addSetting(new NumberSetting("CPS", "Clicks per second", 10.0, 1.0, 20.0, 1.0));
    private long lastAttack;

    public TriggerBot() {
        super("TriggerBot", "Attack entities you look at", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.crosshairTarget == null) return;
        if (mc.crosshairTarget.getType() != HitResult.Type.ENTITY) return;
        if (!(((EntityHitResult) mc.crosshairTarget).getEntity() instanceof LivingEntity target)) return;
        long now = System.currentTimeMillis();
        if (now - lastAttack < 1000.0 / cps.getValue()) return;
        mc.interactionManager.attackEntity(mc.player, target);
        mc.player.swingHand(Hand.MAIN_HAND);
        lastAttack = now;
    }
}
