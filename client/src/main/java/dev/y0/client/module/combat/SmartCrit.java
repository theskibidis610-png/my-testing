package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;

public class SmartCrit extends Module {
    private final BooleanSetting sprintCrit = addSetting(new BooleanSetting("Sprint Crit", "Allow critical hits while sprinting", true));

    public SmartCrit() {
        super("SmartCrit", "Only attacks when a critical hit is possible", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        Entity target = mc.targetedEntity;
        if (!(target instanceof LivingEntity living) || living.isDead()) return;

        boolean canCrit = mc.player.fallDistance > 0
                && !mc.player.isOnGround()
                && !mc.player.isClimbing()
                && !mc.player.isTouchingWater()
                && !mc.player.hasVehicle();

        if (!sprintCrit.getValue() && mc.player.isSprinting()) return;

        if (canCrit && mc.player.getAttackCooldownProgress(0.5f) >= 0.9f) {
            mc.interactionManager.attackEntity(mc.player, target);
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }
}
