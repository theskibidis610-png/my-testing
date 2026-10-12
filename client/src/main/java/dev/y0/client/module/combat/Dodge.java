package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.player.PlayerEntity;

public class Dodge extends Module {
    private final NumberSetting distance = addSetting(new NumberSetting("Distance", "Dodge trigger distance", 3.0, 1.0, 6.0, 0.5));

    public Dodge() {
        super("Dodge", "Auto dodge attacks", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        for (PlayerEntity p : mc.world.getPlayers()) {
            if (p == mc.player) continue;
            if (mc.player.distanceTo(p) < distance.getValue() && p.handSwinging) {
                double dx = mc.player.getX() - p.getX();
                double dz = mc.player.getZ() - p.getZ();
                double len = Math.sqrt(dx * dx + dz * dz);
                if (len > 0) {
                    mc.player.setVelocity(dx / len * 0.5, mc.player.getVelocity().y, dz / len * 0.5);
                }
                return;
            }
        }
    }
}
