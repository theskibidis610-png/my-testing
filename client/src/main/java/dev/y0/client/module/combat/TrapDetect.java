package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.block.Blocks;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class TrapDetect extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Detection range", 5.0, 1.0, 10.0, 1.0));

    public TrapDetect() {
        super("TrapDetect", "Detect traps around you", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        BlockPos center = mc.player.getBlockPos();
        int r = (int) range.getValue();
        for (int x = -r; x <= r; x++) {
            for (int y = -r; y <= r; y++) {
                for (int z = -r; z <= r; z++) {
                    BlockPos pos = center.add(x, y, z);
                    if (mc.world.getBlockState(pos).getBlock() == Blocks.OBSIDIAN || mc.world.getBlockState(pos).getBlock() == Blocks.CRYING_OBSIDIAN) {
                        if (mc.world.getBlockState(pos.up()).getBlock() == Blocks.OBSIDIAN) {
                            mc.player.sendMessage(Text.literal("[Y0] Trap detected!"), true);
                            return;
                        }
                    }
                }
            }
        }
    }
}
