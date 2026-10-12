package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class AutoJump extends Module {
    private final BooleanSetting smart = addSetting(new BooleanSetting("Smart", "Only jump when moving into a block", false));

    public AutoJump() {
        super("AutoJump", "Automatically jump when walking into blocks", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (!mc.player.isOnGround()) return;

        if (smart.getValue()) {
            if (mc.player.horizontalCollision && (mc.player.input.movementForward != 0 || mc.player.input.movementSideways != 0)) {
                mc.player.jump();
            }
        } else {
            if (mc.player.horizontalCollision) {
                mc.player.jump();
            }
        }
    }
}
