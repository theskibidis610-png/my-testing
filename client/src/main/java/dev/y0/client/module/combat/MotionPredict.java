package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.entity.player.PlayerEntity;

public class MotionPredict extends Module {
    private final BooleanSetting extrapolate = addSetting(new BooleanSetting("Extrapolate", "Extrapolate target position", true));

    public MotionPredict() {
        super("MotionPredict", "Predict target motion", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        for (PlayerEntity p : mc.world.getPlayers()) {
            if (p == mc.player) continue;
            if (mc.player.distanceTo(p) > 6.0) continue;
            double predX = p.getX() + (extrapolate.getValue() ? p.getVelocity().x * 2 : 0);
            double predZ = p.getZ() + (extrapolate.getValue() ? p.getVelocity().z * 2 : 0);
            float yaw = (float) Math.toDegrees(Math.atan2(predZ - mc.player.getZ(), predX - mc.player.getX())) - 90;
            mc.player.setYaw(yaw);
            return;
        }
    }
}
