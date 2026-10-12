package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.particle.ParticleTypes;

public class FootPrint extends Module {
    private final BooleanSetting show = addSetting(new BooleanSetting("Show", "Show footprint particles", true));

    public FootPrint() {
        super("FootPrint", "Leaves footprint particles as you walk", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        if (!show.getValue()) return;

        if (mc.player.isOnGround() && mc.player.horizontalSpeed > 0.05) {
            mc.world.addParticle(ParticleTypes.FOOTSTEP,
                    mc.player.getX(), mc.player.getY() + 0.01, mc.player.getZ(),
                    0, 0, 0);
        }
    }
}
