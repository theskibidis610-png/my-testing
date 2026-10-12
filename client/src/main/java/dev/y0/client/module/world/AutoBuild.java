package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class AutoBuild extends Module {
    private final ModeSetting type = addSetting(new ModeSetting("Type", "Structure type to build", "Wall", "Wall", "Tower", "Platform"));
    private final NumberSetting size = addSetting(new NumberSetting("Size", "Height or length of structure", 5.0, 1.0, 20.0, 1.0));

    private int buildProgress;

    public AutoBuild() {
        super("AutoBuild", "Build structures automatically", Category.WORLD);
    }

    @Override
    protected void onEnable() {
        buildProgress = 0;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        if (!(mc.player.getMainHandStack().getItem() instanceof BlockItem)) return;

        int maxSize = (int) size.getValue();
        if (buildProgress >= maxSize) return;

        BlockPos base = mc.player.getBlockPos();
        BlockPos target = switch (type.getValue()) {
            case "Tower" -> base.up(buildProgress + 1);
            case "Platform" -> base.offset(mc.player.getHorizontalFacing(), buildProgress + 1);
            default -> // Wall
                    base.offset(mc.player.getHorizontalFacing(), 1).up(buildProgress);
        };

        if (!mc.world.getBlockState(target).isAir()) {
            buildProgress++;
            return;
        }

        // Find a solid neighbor to place against
        for (Direction dir : Direction.values()) {
            BlockPos neighbor = target.offset(dir);
            if (!mc.world.getBlockState(neighbor).isAir()) {
                BlockHitResult hitResult = new BlockHitResult(
                        Vec3d.ofCenter(neighbor),
                        dir.getOpposite(),
                        neighbor,
                        false
                );
                mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hitResult);
                mc.player.swingHand(Hand.MAIN_HAND);
                buildProgress++;
                return;
            }
        }
    }

    @Override
    protected void onDisable() {
        buildProgress = 0;
    }
}
