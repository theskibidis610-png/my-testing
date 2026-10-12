package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

public class ArmorBreaker extends Module {
    private final NumberSetting priority = addSetting(new NumberSetting("Priority", "Target priority based on armor durability", 1.0, 0.0, 10.0, 1.0));

    public ArmorBreaker() {
        super("ArmorBreaker", "Attack to break armor", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        PlayerEntity target = null;
        double closest = 4.5;
        for (PlayerEntity p : mc.world.getPlayers()) {
            if (p == mc.player) continue;
            double d = mc.player.distanceTo(p);
            if (d < closest) { closest = d; target = p; }
        }
        if (target == null) return;
        mc.interactionManager.attackEntity(mc.player, target);
        mc.player.swingHand(Hand.MAIN_HAND);
    }
}
