package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.entity.effect.StatusEffects;

public class AntiLevitation extends Module {

    public AntiLevitation() {
        super("AntiLevitation", "Removes the levitation effect", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (mc.player.hasStatusEffect(StatusEffects.LEVITATION)) {
            mc.player.removeStatusEffect(StatusEffects.LEVITATION);
        }
    }
}
