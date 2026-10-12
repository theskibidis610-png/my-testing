package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;

public class LavaWalk extends Module {
    private final NumberSetting sinkSpeed = addSetting(new NumberSetting("Sink Speed", "Speed of sinking in lava", 0.0, 0.0, 0.5, 0.05));

    public LavaWalk() {
        super("LavaWalk", "Walk on lava surfaces", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        if (!mc.player.isInLava()) return;

        BlockPos below = mc.player.getBlockPos().down();
        if (mc.world.getBlockState(below).isOf(Blocks.LAVA)) {
            mc.player.setVelocity(mc.player.getVelocity().x, sinkSpeed.getValue() > 0 ? -sinkSpeed.getValue() : 0.05, mc.player.getVelocity().z);
            mc.player.setOnGround(true);
        }
    }
}
