package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;

public class ClickBreak extends Module {

    public ClickBreak() {
        super("ClickBreak", "Only breaks blocks on left click, not hold", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.interactionManager == null) return;

        if (mc.options.attackKey.isPressed() && mc.crosshairTarget instanceof BlockHitResult blockHit) {
            if (blockHit.getType() == HitResult.Type.BLOCK) {
                mc.interactionManager.attackBlock(blockHit.getBlockPos(), blockHit.getSide());
            }
        }
    }
}
