package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class DeathLog extends Module {
    private final BooleanSetting coords = addSetting(new BooleanSetting("Coords", "Include coordinates in death log", true));

    private boolean wasDead;

    public DeathLog() {
        super("DeathLog", "Log death locations and causes", Category.MISC);
    }

    @Override
    protected void onEnable() {
        wasDead = false;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (mc.player.isDead() && !wasDead) {
            String log = "Death at tick " + mc.world.getTime();
            if (coords.getValue()) {
                log += String.format(" [%.1f, %.1f, %.1f]",
                        mc.player.getX(), mc.player.getY(), mc.player.getZ());
            }
            Y0Client.LOGGER.info(log);
        }
        wasDead = mc.player.isDead();
    }
}
