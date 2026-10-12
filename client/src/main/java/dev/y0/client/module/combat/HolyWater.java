package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class HolyWater extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Throw range", 5.0, 1.0, 10.0, 0.5));

    public HolyWater() {
        super("HolyWater", "Splash potions at undead", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        if (mc.player.getMainHandStack().getItem() != Items.SPLASH_POTION) return;
        for (var entity : mc.world.getEntities()) {
            if ((entity instanceof ZombieEntity || entity instanceof SkeletonEntity) && mc.player.distanceTo(entity) < range.getValue()) {
                mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
                return;
            }
        }
    }
}
