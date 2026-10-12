package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;

public class TunnelWalk extends Module {
    private final BooleanSetting avoidLava = addSetting(new BooleanSetting("Avoid Lava", "Stop walking if lava is ahead", true));

    public TunnelWalk() {
        super("TunnelWalk", "Automatically walk forward in tunnels", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        BlockPos ahead = mc.player.getBlockPos().offset(mc.player.getHorizontalFacing());
        if (avoidLava.getValue() && mc.world.getBlockState(ahead).isOf(Blocks.LAVA)) return;

        if (!mc.world.getBlockState(ahead).isAir()) return;
        mc.options.forwardKey.setPressed(true);
    }

    @Override
    protected void onDisable() {
        mc.options.forwardKey.setPressed(false);
    }
}
