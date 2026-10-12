package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class WTap extends Module {
    private boolean sprinting;

    public WTap() {
        super("WTap", "Auto W-tap for extra knockback", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.getAttacking() != null && mc.player.isSprinting()) {
            if (!sprinting) {
                mc.player.setSprinting(false);
                sprinting = true;
            } else {
                mc.player.setSprinting(true);
                sprinting = false;
            }
        }
    }
}
