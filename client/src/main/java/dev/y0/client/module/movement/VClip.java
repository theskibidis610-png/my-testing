package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class VClip extends Module {
    private final NumberSetting blocks = addSetting(new NumberSetting("Blocks", "Vertical distance to clip", 2.0, -10.0, 10.0, 0.5));

    public VClip() {
        super("VClip", "Vertical clip a specific distance", Category.MOVEMENT);
    }

    @Override
    protected void onEnable() {
        if (mc.player != null) {
            mc.player.setPosition(mc.player.getX(), mc.player.getY() + blocks.getValue(), mc.player.getZ());
        }
        toggle();
    }

    @EventHandler
    public void onTick(EventTick event) {}
}
