package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.player.PlayerEntity;

public class KiteBot extends Module {
    private final NumberSetting keepDistance = addSetting(new NumberSetting("Keep Distance", "Distance to maintain", 4.0, 2.0, 8.0, 0.5));

    public KiteBot() {
        super("KiteBot", "Auto kite enemies", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        for (PlayerEntity p : mc.world.getPlayers()) {
            if (p == mc.player) continue;
            double dist = mc.player.distanceTo(p);
            if (dist < keepDistance.getValue()) {
                double dx = mc.player.getX() - p.getX();
                double dz = mc.player.getZ() - p.getZ();
                double len = Math.sqrt(dx * dx + dz * dz);
                if (len > 0) {
                    mc.player.setVelocity(dx / len * 0.3, mc.player.getVelocity().y, dz / len * 0.3);
                }
                return;
            }
        }
    }
}
