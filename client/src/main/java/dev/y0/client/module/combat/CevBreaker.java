package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

public class CevBreaker extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Target range", 4.5, 1.0, 6.0, 0.1));

    public CevBreaker() {
        super("CevBreaker", "Breaks end crystals placed on target head", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        PlayerEntity target = null;
        for (Entity entity : mc.world.getEntities()) {
            if (entity instanceof PlayerEntity player && player != mc.player) {
                if (mc.player.distanceTo(player) <= range.getValue()) {
                    target = player;
                    break;
                }
            }
        }
        if (target == null) return;

        BlockPos headPos = target.getBlockPos().up(2);
        for (Entity entity : mc.world.getEntities()) {
            if (entity instanceof EndCrystalEntity crystal) {
                if (crystal.getBlockPos().equals(headPos)) {
                    mc.interactionManager.attackEntity(mc.player, crystal);
                    mc.player.swingHand(Hand.MAIN_HAND);
                    break;
                }
            }
        }
    }
}
