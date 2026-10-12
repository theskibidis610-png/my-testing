package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class Anchor extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Place range", 4.5, 1.0, 6.0, 0.5));

    public Anchor() {
        super("Anchor", "Auto respawn anchor aura", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        if (mc.world.getDimension().respawnAnchorWorks()) return;

        for (BlockPos pos : BlockPos.iterateOutwards(mc.player.getBlockPos(), 5, 5, 5)) {
            if (!mc.world.getBlockState(pos).isOf(Blocks.RESPAWN_ANCHOR)) continue;
            if (mc.player.squaredDistanceTo(Vec3d.ofCenter(pos)) > range.getValue() * range.getValue()) continue;

            BlockHitResult hit = new BlockHitResult(Vec3d.ofCenter(pos), Direction.UP, pos, false);
            mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
            break;
        }
    }
}
