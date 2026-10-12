package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

public class ClickTP extends Module {
    private final NumberSetting maxDistance = addSetting(new NumberSetting("Max Distance", "Maximum teleport distance", 10.0, 1.0, 50.0, 1.0));

    public ClickTP() {
        super("ClickTP", "Teleport to cursor position on right click", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        if (!mc.options.useKey.isPressed()) return;

        Vec3d eyePos = mc.player.getEyePos();
        Vec3d lookVec = mc.player.getRotationVec(1.0f);
        Vec3d endPos = eyePos.add(lookVec.multiply(maxDistance.getValue()));

        BlockHitResult result = mc.world.raycast(new RaycastContext(
                eyePos, endPos, RaycastContext.ShapeType.OUTLINE,
                RaycastContext.FluidHandling.NONE, mc.player));

        if (result.getType() != HitResult.Type.MISS) {
            Vec3d target = result.getPos();
            mc.player.setPosition(target.x, target.y, target.z);
        }
    }
}
