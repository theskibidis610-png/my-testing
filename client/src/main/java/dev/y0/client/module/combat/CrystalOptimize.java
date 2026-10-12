package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class CrystalOptimize extends Module {
    private final BooleanSetting predict = addSetting(new BooleanSetting("Predict", "Predict crystal placement", true));
    private final BooleanSetting multiplace = addSetting(new BooleanSetting("Multiplace", "Place multiple crystals per tick", false));

    public CrystalOptimize() {
        super("CrystalOptimize", "Optimized end crystal placing and breaking", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        for (Entity entity : mc.world.getEntities()) {
            if (entity instanceof EndCrystalEntity crystal) {
                if (mc.player.distanceTo(crystal) <= 6.0) {
                    mc.interactionManager.attackEntity(mc.player, crystal);
                    mc.player.swingHand(Hand.MAIN_HAND);
                    if (!multiplace.getValue()) break;
                }
            }
        }

        if (mc.player.getMainHandStack().getItem() != Items.END_CRYSTAL
                && mc.player.getOffHandStack().getItem() != Items.END_CRYSTAL) return;

        PlayerEntity target = null;
        for (Entity entity : mc.world.getEntities()) {
            if (entity instanceof PlayerEntity player && player != mc.player) {
                if (mc.player.distanceTo(player) <= 6.0) {
                    target = player;
                    break;
                }
            }
        }
        if (target == null) return;

        BlockPos placePos = target.getBlockPos().down();
        Hand hand = mc.player.getMainHandStack().getItem() == Items.END_CRYSTAL ? Hand.MAIN_HAND : Hand.OFF_HAND;
        mc.interactionManager.interactBlock(mc.player, hand,
                new BlockHitResult(Vec3d.ofCenter(placePos), Direction.UP, placePos, false));
    }
}
