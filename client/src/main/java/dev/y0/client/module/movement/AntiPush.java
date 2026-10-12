package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class AntiPush extends Module {
    private final BooleanSetting players = addSetting(new BooleanSetting("Players", "Prevent player pushing", true));
    private final BooleanSetting water = addSetting(new BooleanSetting("Water", "Prevent water pushing", true));
    private final BooleanSetting blocks = addSetting(new BooleanSetting("Blocks", "Prevent block pushing", true));

    public AntiPush() {
        super("AntiPush", "Prevent entity pushing", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (players.getValue() || blocks.getValue()) {
            mc.player.setVelocity(
                    mc.player.getVelocity().x,
                    mc.player.getVelocity().y,
                    mc.player.getVelocity().z
            );
        }
    }

    public boolean shouldPreventPlayerPush() {
        return isEnabled() && players.getValue();
    }

    public boolean shouldPreventWaterPush() {
        return isEnabled() && water.getValue();
    }

    public boolean shouldPreventBlockPush() {
        return isEnabled() && blocks.getValue();
    }
}
