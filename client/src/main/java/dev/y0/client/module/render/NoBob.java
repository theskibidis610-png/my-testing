package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class NoBob extends Module {

    public NoBob() {
        super("NoBob", "Remove view bobbing", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        mc.options.getBobView().setValue(false);
    }

    @Override
    protected void onDisable() {
        if (mc.options != null) {
            mc.options.getBobView().setValue(true);
        }
    }
}
