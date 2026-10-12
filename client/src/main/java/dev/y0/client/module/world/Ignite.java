package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class Ignite extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Ignite range", 4.0, 1.0, 6.0, 0.5));

    public Ignite() {
        super("Ignite", "Auto ignite nearby players with flint and steel", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        // Find flint and steel in hotbar
        int flintSlot = findFlintAndSteel();
        if (flintSlot == -1) return;

        // Find nearest player
        PlayerEntity target = null;
        double closest = range.getValue();

        for (PlayerEntity player : mc.world.getPlayers()) {
            if (player == mc.player) continue;
            double dist = mc.player.distanceTo(player);
            if (dist < closest) {
                closest = dist;
                target = player;
            }
        }

        if (target == null) return;

        // Place fire at the target's feet
        BlockPos targetPos = target.getBlockPos();
        if (!mc.world.getBlockState(targetPos).isAir()) return;

        int prevSlot = mc.player.getInventory().selectedSlot;
        mc.player.getInventory().selectedSlot = flintSlot;

        BlockHitResult hitResult = new BlockHitResult(
                Vec3d.ofCenter(targetPos.down()),
                Direction.UP,
                targetPos.down(),
                false
        );
        mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hitResult);
        mc.player.swingHand(Hand.MAIN_HAND);

        mc.player.getInventory().selectedSlot = prevSlot;
    }

    private int findFlintAndSteel() {
        for (int i = 0; i < 9; i++) {
            if (mc.player.getInventory().getStack(i).isOf(Items.FLINT_AND_STEEL)) {
                return i;
            }
        }
        return -1;
    }
}
