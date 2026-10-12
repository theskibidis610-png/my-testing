package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.text.Text;

public class DeathCoords extends Module {
    private final BooleanSetting chatMessage = addSetting(new BooleanSetting("Chat Message", "Show death coordinates in chat", true));

    private boolean wasDead;

    public DeathCoords() {
        super("DeathCoords", "Logs your coordinates on death", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        if (mc.player.isDead() && !wasDead) {
            wasDead = true;
            int x = (int) mc.player.getX();
            int y = (int) mc.player.getY();
            int z = (int) mc.player.getZ();
            String coords = String.format("Death at X: %d Y: %d Z: %d", x, y, z);

            if (chatMessage.getValue()) {
                mc.player.sendMessage(Text.literal("[DeathCoords] " + coords), false);
            }
        } else if (!mc.player.isDead()) {
            wasDead = false;
        }
    }
}
