package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.Hand;

public class EndCrystalAura extends Module {
    private final NumberSetting breakDelay = addSetting(new NumberSetting("Break Delay", "Delay between breaking crystals in ticks", 2.0, 0.0, 20.0, 1.0));
    private int tickCounter;

    public EndCrystalAura() {
        super("EndCrystalAura", "End crystal specific aura", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        tickCounter++;
        if (tickCounter < breakDelay.getValue()) return;
        for (var entity : mc.world.getEntities()) {
            if (entity instanceof EndCrystalEntity crystal && mc.player.distanceTo(crystal) < 6.0) {
                mc.interactionManager.attackEntity(mc.player, crystal);
                mc.player.swingHand(Hand.MAIN_HAND);
                tickCounter = 0;
                return;
            }
        }
    }
}
