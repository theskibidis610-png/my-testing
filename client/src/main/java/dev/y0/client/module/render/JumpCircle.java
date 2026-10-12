package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

public class JumpCircle extends Module {
    private final NumberSetting radius = addSetting(new NumberSetting("Radius", "Circle radius", 1.0, 0.5, 5.0, 0.1));

    private Vec3d lastJumpPos = null;
    private boolean wasOnGround = true;

    public JumpCircle() {
        super("JumpCircle", "Show circle at last jump position", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        boolean onGround = mc.player.isOnGround();
        if (wasOnGround && !onGround) {
            lastJumpPos = mc.player.getPos();
        }
        wasOnGround = onGround;
    }

    public Vec3d getLastJumpPos() {
        return lastJumpPos;
    }

    public double getRadius() {
        return radius.getValue();
    }
}
