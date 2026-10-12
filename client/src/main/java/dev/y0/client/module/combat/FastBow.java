package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.BowItem;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class FastBow extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Packets per tick", 5.0, 1.0, 10.0, 1.0));

    public FastBow() {
        super("FastBow", "Shoots arrows rapidly using packet manipulation", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (!(mc.player.getMainHandStack().getItem() instanceof BowItem)) return;
        if (!mc.player.isUsingItem()) return;

        for (int i = 0; i < (int) speed.getValue(); i++) {
            mc.player.networkHandler.sendPacket(new PlayerActionC2SPacket(
                    PlayerActionC2SPacket.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, Direction.UP));
        }
        mc.interactionManager.stopUsingItem(mc.player);
    }
}
