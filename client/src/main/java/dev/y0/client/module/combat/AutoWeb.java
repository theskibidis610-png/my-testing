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

public class AutoWeb extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Place range", 3.0, 1.0, 5.0, 0.5));

    public AutoWeb() {
        super("AutoWeb", "Places webs on nearby enemies", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        int webSlot = -1;
        for (int i = 0; i < 9; i++) {
            if (mc.player.getInventory().getStack(i).getItem() == Items.COBWEB) {
                webSlot = i;
                break;
            }
        }
        if (webSlot == -1) return;

        for (Entity entity : mc.world.getEntities()) {
            if (entity instanceof PlayerEntity player && player != mc.player) {
                if (mc.player.distanceTo(player) <= range.getValue()) {
                    int oldSlot = mc.player.getInventory().selectedSlot;
                    mc.player.getInventory().selectedSlot = webSlot;
                    BlockPos pos = player.getBlockPos();
                    mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND,
                            new BlockHitResult(Vec3d.ofCenter(pos), Direction.UP, pos.down(), false));
                    mc.player.getInventory().selectedSlot = oldSlot;
                    break;
                }
            }
        }
    }
}
