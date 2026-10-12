package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.player.PlayerEntity;

public class SpeedFight extends Module {
    private final NumberSetting boost = addSetting(new NumberSetting("Boost", "Speed boost multiplier", 1.5, 1.0, 3.0, 0.1));

    public SpeedFight() {
        super("SpeedFight", "Move faster while fighting", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        boolean inCombat = false;
        for (PlayerEntity p : mc.world.getPlayers()) {
            if (p != mc.player && mc.player.distanceTo(p) < 6.0) { inCombat = true; break; }
        }
        if (inCombat) {
            mc.player.setVelocity(
                    mc.player.getVelocity().x * boost.getValue(),
                    mc.player.getVelocity().y,
                    mc.player.getVelocity().z * boost.getValue());
        }
    }
}
