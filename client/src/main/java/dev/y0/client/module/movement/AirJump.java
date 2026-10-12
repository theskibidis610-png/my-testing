package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class AirJump extends Module {
    private final NumberSetting maxJumps = addSetting(new NumberSetting("Max Jumps", "Maximum mid-air jumps", 2, 1, 5, 1));
    private int jumpsLeft;

    public AirJump() {
        super("AirJump", "Jump while in mid-air", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (mc.player.isOnGround()) {
            jumpsLeft = (int) maxJumps.getValue();
        } else if (mc.options.jumpKey.isPressed() && jumpsLeft > 0) {
            mc.player.jump();
            jumpsLeft--;
        }
    }
}
