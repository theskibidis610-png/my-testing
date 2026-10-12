package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Random;

public class StrafeLock extends Module {
    private final ModeSetting direction = addSetting(new ModeSetting("Direction", "Strafe direction", "Left", "Left", "Right", "Random"));
    private final Random random = new Random();

    public StrafeLock() {
        super("StrafeLock", "Lock strafe during combat", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        boolean inCombat = false;
        for (PlayerEntity p : mc.world.getPlayers()) {
            if (p != mc.player && mc.player.distanceTo(p) < 5.0) { inCombat = true; break; }
        }
        if (!inCombat) return;
        boolean left = switch (direction.getValue()) {
            case "Right" -> false;
            case "Random" -> random.nextBoolean();
            default -> true;
        };
        mc.options.leftKey.setPressed(left);
        mc.options.rightKey.setPressed(!left);
    }
}
