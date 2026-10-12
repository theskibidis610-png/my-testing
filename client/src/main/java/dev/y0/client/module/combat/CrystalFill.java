package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class CrystalFill extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Fill range", 4.0, 1.0, 6.0, 0.5));

    public CrystalFill() {
        super("CrystalFill", "Fill obsidian holes with crystals", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        if (mc.player.getMainHandStack().getItem() != Items.END_CRYSTAL) return;
        BlockPos center = mc.player.getBlockPos();
        int r = (int) range.getValue();
        for (int x = -r; x <= r; x++) {
            for (int z = -r; z <= r; z++) {
                BlockPos pos = center.add(x, 0, z);
                if (mc.world.getBlockState(pos).getBlock() == Blocks.OBSIDIAN && mc.world.getBlockState(pos.up()).isAir()) {
                    mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND,
                            new BlockHitResult(Vec3d.ofCenter(pos), Direction.UP, pos, false));
                    return;
                }
            }
        }
    }
}
