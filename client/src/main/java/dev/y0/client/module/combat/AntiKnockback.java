package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class AntiKnockback extends Module {
    private final NumberSetting horizontal = addSetting(new NumberSetting("Horizontal", "Horizontal knockback multiplier", 0.0, 0.0, 1.0, 0.05));
    private final NumberSetting vertical = addSetting(new NumberSetting("Vertical", "Vertical knockback multiplier", 0.0, 0.0, 1.0, 0.05));

    public AntiKnockback() {
        super("AntiKnockback", "Reduces knockback taken from attacks", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (mc.player.hurtTime > 0) {
            mc.player.setVelocity(
                    mc.player.getVelocity().x * horizontal.getValue(),
                    mc.player.getVelocity().y * vertical.getValue(),
                    mc.player.getVelocity().z * horizontal.getValue()
            );
        }
    }
}
