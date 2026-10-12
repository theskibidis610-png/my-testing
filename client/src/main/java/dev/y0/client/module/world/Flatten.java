package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class Flatten extends Module {
    private final NumberSetting radius = addSetting(new NumberSetting("Radius", "Flatten radius around player", 3.0, 1.0, 8.0, 1.0));

    public Flatten() {
        super("Flatten", "Flatten terrain around you at your Y level", Category.WORLD);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        int r = (int) radius.getValue();
        BlockPos feet = mc.player.getBlockPos();
        int targetY = feet.getY();

        for (int x = -r; x <= r; x++) {
            for (int z = -r; z <= r; z++) {
                // Break blocks above the player's foot level
                for (int y = targetY; y <= targetY + 4; y++) {
                    BlockPos pos = new BlockPos(feet.getX() + x, y, feet.getZ() + z);
                    if (mc.world.getBlockState(pos).isAir()) continue;
                    if (mc.world.getBlockState(pos).getHardness(mc.world, pos) < 0) continue;

                    mc.interactionManager.attackBlock(pos, Direction.UP);
                    mc.interactionManager.updateBlockBreakingProgress(pos, Direction.UP);
                    return;
                }
            }
        }
    }
}
