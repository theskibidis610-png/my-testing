package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class GlowESP extends Module {
    private final BooleanSetting friendColor = addSetting(new BooleanSetting("FriendColor", "Use team color for friends", true));

    public GlowESP() {
        super("GlowESP", "Team-colored entity glow effect", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null) return;
        // Apply glowing effect to entities with team colors
    }

    public boolean useFriendColor() {
        return friendColor.getValue();
    }
}
