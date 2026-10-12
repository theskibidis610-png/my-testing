package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class AntiGhostBlock extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Resync radius", 3.0, 1.0, 6.0, 1.0));
    private int tickCounter;

    public AntiGhostBlock() {
        super("AntiGhostBlock", "Fix ghost blocks by resyncing nearby blocks", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        tickCounter++;
        if (tickCounter < 20) return;
        tickCounter = 0;

        int r = (int) range.getValue();
        BlockPos center = mc.player.getBlockPos();

        for (BlockPos pos : BlockPos.iterateOutwards(center, r, r, r)) {
            if (mc.world.getBlockState(pos).isAir()) continue;
            mc.player.networkHandler.sendPacket(new PlayerActionC2SPacket(
                    PlayerActionC2SPacket.Action.ABORT_DESTROY_BLOCK, pos, Direction.UP
            ));
        }
    }
}
