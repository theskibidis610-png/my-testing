package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.projectile.ArrowEntity;

public class ArrowDodge extends Module {
    private final NumberSetting reaction = addSetting(new NumberSetting("Reaction", "Dodge reaction distance", 3.0, 1.0, 8.0, 0.5));

    public ArrowDodge() {
        super("ArrowDodge", "Dodge incoming arrows", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        for (var entity : mc.world.getEntities()) {
            if (entity instanceof ArrowEntity arrow && !arrow.isOnGround()) {
                if (mc.player.distanceTo(arrow) < reaction.getValue()) {
                    double dx = arrow.getVelocity().x;
                    double dz = arrow.getVelocity().z;
                    double perpX = -dz;
                    double perpZ = dx;
                    double len = Math.sqrt(perpX * perpX + perpZ * perpZ);
                    if (len > 0) {
                        mc.player.setVelocity(perpX / len * 0.4, mc.player.getVelocity().y, perpZ / len * 0.4);
                    }
                    return;
                }
            }
        }
    }
}
