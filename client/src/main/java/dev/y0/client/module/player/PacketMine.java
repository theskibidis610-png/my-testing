package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class PacketMine extends Module {
    private final NumberSetting mineSpeed = addSetting(new NumberSetting("Speed", "Mining speed multiplier", 1.0, 0.1, 2.0, 0.1));

    private BlockPos miningPos;
    private Direction miningFace;
    private float miningProgress;

    public PacketMine() {
        super("PacketMine", "Mine blocks faster using packets", Category.PLAYER);
    }

    @Override
    protected void onDisable() {
        miningPos = null;
        miningFace = null;
        miningProgress = 0;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        if (!mc.options.attackKey.isPressed()) {
            miningPos = null;
            miningProgress = 0;
            return;
        }

        if (mc.crosshairTarget == null || mc.crosshairTarget.getType() != HitResult.Type.BLOCK) return;

        BlockHitResult blockHit = (BlockHitResult) mc.crosshairTarget;
        BlockPos pos = blockHit.getBlockPos();
        Direction face = blockHit.getSide();

        if (mc.world.getBlockState(pos).isAir()) return;

        // New block target
        if (miningPos == null || !miningPos.equals(pos)) {
            miningPos = pos;
            miningFace = face;
            miningProgress = 0;

            mc.player.networkHandler.sendPacket(
                    new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.START_DESTROY_BLOCK, pos, face)
            );
        }

        // Accumulate progress with speed multiplier
        miningProgress += (float) (mc.world.getBlockState(pos).calcBlockBreakingDelta(mc.player, mc.world, pos) * mineSpeed.getValue());

        if (miningProgress >= 1.0f) {
            mc.player.networkHandler.sendPacket(
                    new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK, miningPos, miningFace)
            );
            miningPos = null;
            miningProgress = 0;
        }

        // Note: For full functionality, a mixin on ClientPlayerInteractionManager
        // is recommended to intercept block breaking and apply the speed multiplier
        // to the actual breaking progress.
    }
}
