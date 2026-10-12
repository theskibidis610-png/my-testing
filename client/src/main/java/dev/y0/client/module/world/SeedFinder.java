package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.text.Text;

public class SeedFinder extends Module {
    private boolean searched;

    public SeedFinder() {
        super("SeedFinder", "Attempt to find the world seed from visible structures", Category.WORLD);
    }

    @Override
    protected void onEnable() {
        searched = false;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        if (searched) return;
        searched = true;

        // Placeholder: In singleplayer, the seed is accessible via the integrated server
        if (mc.isInSingleplayer() && mc.getServer() != null) {
            long seed = mc.getServer().getOverworld().getSeed();
            mc.player.sendMessage(Text.literal("Seed: " + seed), false);
        } else {
            mc.player.sendMessage(Text.literal("SeedFinder: Cannot determine seed on this server."), false);
            mc.player.sendMessage(Text.literal("Analyze biomes and structures manually."), false);
        }

        disable();
    }
}
