package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class Crawl extends Module {

    public Crawl() {
        super("Crawl", "Force crawling state", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        mc.player.setPose(net.minecraft.entity.EntityPose.SWIMMING);
    }
}
