package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.particle.ParticleTypes;

public class Particles extends Module {
    private final NumberSetting multiplier = addSetting(new NumberSetting("Multiplier", "Particle count multiplier", 2.0, 1.0, 10.0, 1.0));
    private final BooleanSetting alwaysCrit = addSetting(new BooleanSetting("Always Crit", "Always show crit particles on hit", false));

    public Particles() {
        super("Particles", "Enhanced particle effects", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        // Spawn extra crit particles around the player's attack target
        if (alwaysCrit.getValue() && mc.player.handSwinging) {
            double x = mc.player.getX();
            double y = mc.player.getY() + 1.0;
            double z = mc.player.getZ();
            int count = (int) multiplier.getValue();
            for (int i = 0; i < count; i++) {
                double ox = (mc.player.getRandom().nextDouble() - 0.5) * 2.0;
                double oy = mc.player.getRandom().nextDouble();
                double oz = (mc.player.getRandom().nextDouble() - 0.5) * 2.0;
                mc.world.addParticle(ParticleTypes.CRIT, x + ox, y + oy, z + oz, 0, 0, 0);
            }
        }

        // Full particle multiplication requires a mixin on ParticleManager.addParticle
        // to duplicate each spawned particle by the multiplier amount.
    }

    public int getMultiplier() {
        return (int) multiplier.getValue();
    }
}
