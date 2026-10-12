package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.entity.effect.StatusEffects;

public class AntiBlind extends Module {

    public AntiBlind() {
        super("AntiBlind", "Remove blindness effect from screen", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        mc.player.removeStatusEffect(StatusEffects.BLINDNESS);
        mc.player.removeStatusEffect(StatusEffects.DARKNESS);
    }
}
