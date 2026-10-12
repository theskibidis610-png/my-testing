package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.text.Text;

public class ExperienceAlert extends Module {
    private final NumberSetting targetLevel = addSetting(new NumberSetting("Target Level", "Alert when reaching this XP level", 30.0, 1.0, 100.0, 1.0));

    private boolean alerted;

    public ExperienceAlert() {
        super("ExperienceAlert", "Alerts you when you reach a target XP level", Category.PLAYER);
    }

    @Override
    protected void onEnable() {
        alerted = false;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (mc.player.experienceLevel >= (int) targetLevel.getValue()) {
            if (!alerted) {
                alerted = true;
                mc.player.sendMessage(Text.literal("[ExperienceAlert] Reached level " + mc.player.experienceLevel + "!"), false);
            }
        } else {
            alerted = false;
        }
    }
}
