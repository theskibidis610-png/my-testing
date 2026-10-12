package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.Box;

public class AutoCollect extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Collection range", 5.0, 1.0, 10.0, 0.5));

    public AutoCollect() {
        super("AutoCollect", "Automatically collect nearby items", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        double r = range.getValue();
        Box box = mc.player.getBoundingBox().expand(r);

        for (ItemEntity item : mc.world.getEntitiesByClass(ItemEntity.class, box, e -> true)) {
            mc.player.setVelocity(item.getPos().subtract(mc.player.getPos()).normalize().multiply(0.4));
            return;
        }
    }
}
