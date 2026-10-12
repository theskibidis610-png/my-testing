package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class AutoTeam extends Module {

    private boolean joined;

    public AutoTeam() {
        super("AutoTeam", "Automatically accept team invitations", Category.MISC);
    }

    @Override
    protected void onEnable() {
        joined = false;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.getNetworkHandler() == null) return;
        // Team join detection would be handled by a mixin on
        // incoming chat messages matching team invite patterns.
    }

    public boolean hasJoined() {
        return joined;
    }
}
