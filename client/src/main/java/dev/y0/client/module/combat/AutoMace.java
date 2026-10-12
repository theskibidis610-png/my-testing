package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.Items;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

public class AutoMace extends Module {
    private final NumberSetting minHeight = addSetting(new NumberSetting("Min Height", "Minimum fall height to attack", 3.0, 1.0, 20.0, 0.5));

    public AutoMace() {
        super("AutoMace", "Auto use mace at optimal height", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        if (mc.player.getMainHandStack().getItem() != Items.MACE) return;
        if (mc.player.fallDistance < minHeight.getValue()) return;
        for (PlayerEntity p : mc.world.getPlayers()) {
            if (p == mc.player) continue;
            if (mc.player.distanceTo(p) < 4.0) {
                mc.interactionManager.attackEntity(mc.player, p);
                mc.player.swingHand(Hand.MAIN_HAND);
                return;
            }
        }
    }
}
