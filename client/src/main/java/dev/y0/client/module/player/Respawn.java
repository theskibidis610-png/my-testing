package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.text.Text;

public class Respawn extends Module {
    private final BooleanSetting bed = addSetting(new BooleanSetting("Bed", "Respawn at bed if set", true));
    private final BooleanSetting anchor = addSetting(new BooleanSetting("Anchor", "Respawn at anchor if set", false));

    public Respawn() {
        super("Respawn", "Customizes respawn behavior preferences", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (mc.player.isDead()) {
            mc.player.sendMessage(Text.literal("Respawning with preferences: Bed=" +
                    bed.getValue() + " Anchor=" + anchor.getValue()), true);
            mc.player.requestRespawn();
        }
    }
}
