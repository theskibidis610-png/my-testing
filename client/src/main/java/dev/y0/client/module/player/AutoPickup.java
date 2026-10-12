package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.ItemEntity;

public class AutoPickup extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Pickup range in blocks", 5.0, 1.0, 10.0, 0.5));

    public AutoPickup() {
        super("AutoPickup", "Automatically walks toward nearby dropped items", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        ItemEntity closest = null;
        double closestDist = range.getValue();

        for (var entity : mc.world.getEntities()) {
            if (entity instanceof ItemEntity item) {
                double dist = mc.player.distanceTo(item);
                if (dist < closestDist) {
                    closestDist = dist;
                    closest = item;
                }
            }
        }

        if (closest != null) {
            mc.player.lookAt(closest.getCommandSource().getEntityAnchor(), closest.getPos());
        }
    }
}
