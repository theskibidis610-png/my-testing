package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.entity.passive.AbstractHorseEntity;

public class HorseJump extends Module {

    public HorseJump() {
        super("HorseJump", "Always max horse jump power", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.getVehicle() instanceof AbstractHorseEntity) {
            mc.player.setMountJumpStrength(1.0f);
        }
    }
}
