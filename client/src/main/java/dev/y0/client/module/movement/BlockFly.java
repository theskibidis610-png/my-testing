package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class BlockFly extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Flight speed while placing blocks", 0.5, 0.1, 2.0, 0.1));

    public BlockFly() {
        super("BlockFly", "Fly by continuously placing blocks below", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (!mc.player.isOnGround()) {
            mc.player.setVelocity(
                    mc.player.getVelocity().x * speed.getValue(),
                    mc.player.getVelocity().y > 0 ? mc.player.getVelocity().y : -0.05,
                    mc.player.getVelocity().z * speed.getValue()
            );
        }
    }
}
