package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Aura extends Module {
    private final NumberSetting maxTargets = addSetting(new NumberSetting("Max Targets", "Maximum simultaneous targets", 3.0, 1.0, 5.0, 1.0));
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Attack range", 4.0, 1.0, 6.0, 0.1));

    public Aura() {
        super("Aura", "Attacks multiple nearby targets simultaneously", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        List<LivingEntity> targets = new ArrayList<>();
        for (Entity entity : mc.world.getEntities()) {
            if (entity == mc.player) continue;
            if (!(entity instanceof LivingEntity living)) continue;
            if (living.isDead() || living.getHealth() <= 0) continue;
            if (!(entity instanceof PlayerEntity)) continue;
            if (mc.player.distanceTo(entity) > range.getValue()) continue;
            targets.add(living);
        }

        targets.sort(Comparator.comparingDouble(e -> mc.player.distanceTo(e)));
        int count = Math.min(targets.size(), (int) maxTargets.getValue());

        for (int i = 0; i < count; i++) {
            mc.interactionManager.attackEntity(mc.player, targets.get(i));
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }
}
