package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class FastLadder extends Module {
    private final NumberSetting downSpeed = addSetting(new NumberSetting("Down Speed", "Ladder descend speed", 0.5, 0.1, 2.0, 0.1));

    public FastLadder() {
        super("FastLadder", "Descend ladders faster", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.isClimbing() && !mc.options.jumpKey.isPressed()) {
            mc.player.setVelocity(
                    mc.player.getVelocity().x,
                    -downSpeed.getValue(),
                    mc.player.getVelocity().z
            );
        }
    }
}
