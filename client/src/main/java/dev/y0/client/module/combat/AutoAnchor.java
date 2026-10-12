package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class AutoAnchor extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Target range", 4.0, 1.0, 6.0, 0.5));

    public AutoAnchor() {
        super("AutoAnchor", "Automates respawn anchor combat", Category.COMBAT);
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

        int anchorSlot = -1;
        int glowstoneSlot = -1;
        for (int i = 0; i < 9; i++) {
            if (mc.player.getInventory().getStack(i).getItem() == Items.RESPAWN_ANCHOR) anchorSlot = i;
            if (mc.player.getInventory().getStack(i).getItem() == Items.GLOWSTONE) glowstoneSlot = i;
        }
        if (anchorSlot == -1 || glowstoneSlot == -1) return;

        BlockPos placePos = target.getBlockPos();
        int oldSlot = mc.player.getInventory().selectedSlot;
        BlockHitResult hitResult = new BlockHitResult(Vec3d.ofCenter(placePos), Direction.UP, placePos.down(), false);

        mc.player.getInventory().selectedSlot = anchorSlot;
        mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hitResult);

        mc.player.getInventory().selectedSlot = glowstoneSlot;
        mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND,
                new BlockHitResult(Vec3d.ofCenter(placePos), Direction.UP, placePos, false));

        mc.player.getInventory().selectedSlot = oldSlot;
    }
}
