package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;

public class HitSelect extends Module {
    private final NumberSetting cooldownThreshold = addSetting(new NumberSetting("Cooldown", "Attack cooldown threshold", 0.9, 0.5, 1.0, 0.05));

    public HitSelect() {
        super("HitSelect", "Only attacks at optimal attack cooldown timing", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        float cooldown = mc.player.getAttackCooldownProgress(0.5f);
        if (cooldown < cooldownThreshold.getValue()) return;

        Entity target = mc.targetedEntity;
        if (target instanceof LivingEntity living && !living.isDead()) {
            mc.interactionManager.attackEntity(mc.player, target);
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }
}
