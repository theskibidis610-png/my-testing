package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;

public class BowAimbot extends Module {
    private final BooleanSetting predict = addSetting(new BooleanSetting("Predict", "Predict target movement", true));

    public BowAimbot() {
        super("BowAimbot", "Auto aim bow at nearest player", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        if (mc.player.getMainHandStack().getItem() != Items.BOW) return;
        if (!mc.player.isUsingItem()) return;

        PlayerEntity target = null;
        double closest = 64;
        for (PlayerEntity p : mc.world.getPlayers()) {
            if (p == mc.player) continue;
            double d = mc.player.distanceTo(p);
            if (d < closest) { closest = d; target = p; }
        }
        if (target == null) return;

        double dx = target.getX() - mc.player.getX();
        double dy = target.getEyeY() - mc.player.getEyeY();
        double dz = target.getZ() - mc.player.getZ();
        double dist = Math.sqrt(dx * dx + dz * dz);
        float yaw = (float) Math.toDegrees(Math.atan2(dz, dx)) - 90f;
        float pitch = (float) -Math.toDegrees(Math.atan2(dy + dist * 0.2, dist));

        mc.player.setYaw(yaw);
        mc.player.setPitch(pitch);
    }
}
