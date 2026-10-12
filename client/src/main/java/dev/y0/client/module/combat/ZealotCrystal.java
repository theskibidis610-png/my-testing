package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class ZealotCrystal extends Module {
    private final BooleanSetting antiSelf = addSetting(new BooleanSetting("Anti Self", "Avoid self damage", true));

    public ZealotCrystal() {
        super("ZealotCrystal", "Aggressive crystal combat", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        // Break nearby crystals
        for (var entity : mc.world.getEntities()) {
            if (entity instanceof EndCrystalEntity crystal) {
                if (antiSelf.getValue() && mc.player.distanceTo(crystal) < 3.0) continue;
                if (mc.player.distanceTo(crystal) < 6.0) {
                    mc.interactionManager.attackEntity(mc.player, crystal);
                    mc.player.swingHand(Hand.MAIN_HAND);
                    return;
                }
            }
        }
        // Place crystals near targets
        if (mc.player.getMainHandStack().getItem() != Items.END_CRYSTAL) return;
        for (PlayerEntity p : mc.world.getPlayers()) {
            if (p == mc.player || mc.player.distanceTo(p) > 5.0) continue;
            BlockPos pos = p.getBlockPos().down();
            mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND,
                    new BlockHitResult(Vec3d.ofCenter(pos), Direction.UP, pos, false));
            return;
        }
    }
}
