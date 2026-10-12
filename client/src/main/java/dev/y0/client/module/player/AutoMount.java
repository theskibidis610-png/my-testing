package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.util.Hand;

public class AutoMount extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Range to search for mountable entities", 4.0, 1.0, 8.0, 0.5));

    public AutoMount() {
        super("AutoMount", "Automatically mounts nearby rideable entities", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.interactionManager == null) return;
        if (mc.player.hasVehicle()) return;

        double r = range.getValue();
        for (Entity entity : mc.world.getEntities()) {
            if (entity.squaredDistanceTo(mc.player) > r * r) continue;
            if (entity instanceof AbstractHorseEntity || entity instanceof BoatEntity || entity instanceof MinecartEntity) {
                mc.interactionManager.interactEntity(mc.player, entity, Hand.MAIN_HAND);
                return;
            }
        }
    }
}
