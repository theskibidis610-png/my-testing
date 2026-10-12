package dev.y0.client.module.misc;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class BetterChat extends Module {
    private final BooleanSetting timestamps = addSetting(new BooleanSetting("Timestamps", "Show timestamps in chat", true));
    private final BooleanSetting infiniteHistory = addSetting(new BooleanSetting("Infinite History", "Keep unlimited chat history", true));

    public BetterChat() {
        super("BetterChat", "Various chat improvements", Category.MISC);
    }

    // NOTE: Timestamps require a mixin on net.minecraft.client.gui.hud.ChatHud
    // to prepend formatted time to each incoming message.
    // Infinite history requires a mixin to remove the 100-message limit
    // in ChatHud.addMessage().

    public boolean showTimestamps() {
        return isEnabled() && timestamps.getValue();
    }

    public boolean hasInfiniteHistory() {
        return isEnabled() && infiniteHistory.getValue();
    }
}
