package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.util.Hand;

public class ComboLock extends Module {
    private final NumberSetting maxHits = addSetting(new NumberSetting("Max Hits", "Hits before switching target", 5.0, 1.0, 20.0, 1.0));
    private Entity currentTarget;
    private int hitCount;

    public ComboLock() {
        super("ComboLock", "Lock combos on targets", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        if (currentTarget == null || !currentTarget.isAlive() || hitCount >= maxHits.getValue()) {
            currentTarget = null;
            hitCount = 0;
        }
        if (currentTarget == null) {
            for (Entity e : mc.world.getEntities()) {
                if (e == mc.player || !e.isAlive()) continue;
                if (mc.player.distanceTo(e) < 4.5) { currentTarget = e; break; }
            }
        }
        if (currentTarget != null && mc.player.distanceTo(currentTarget) < 4.5) {
            mc.interactionManager.attackEntity(mc.player, currentTarget);
            mc.player.swingHand(Hand.MAIN_HAND);
            hitCount++;
        }
    }
}
