package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.util.math.BlockPos;

public class AutoSneak extends Module {
    private final BooleanSetting onlyAtEdge = addSetting(new BooleanSetting("Only At Edge", "Only sneak when near a block edge", true));

    public AutoSneak() {
        super("AutoSneak", "Automatically sneaks to prevent falling off edges", Category.PLAYER);
    }

    @Override
    protected void onDisable() {
        if (mc.player != null) {
            mc.options.sneakKey.setPressed(false);
        }
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || !mc.player.isOnGround()) return;

        if (!onlyAtEdge.getValue()) {
            mc.options.sneakKey.setPressed(true);
            return;
        }

        // Check if the block below the player's bounding box edge is air
        double x = mc.player.getX();
        double z = mc.player.getZ();
        double y = mc.player.getY() - 1;

        boolean nearEdge = mc.world.isAir(BlockPos.ofFloored(x + 0.3, y, z + 0.3))
                || mc.world.isAir(BlockPos.ofFloored(x - 0.3, y, z - 0.3))
                || mc.world.isAir(BlockPos.ofFloored(x + 0.3, y, z - 0.3))
                || mc.world.isAir(BlockPos.ofFloored(x - 0.3, y, z + 0.3));

        mc.options.sneakKey.setPressed(nearEdge);
    }
}
