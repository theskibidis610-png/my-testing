package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class ChatFilter extends Module {
    private final BooleanSetting profanity = addSetting(new BooleanSetting("Profanity Filter", "Filter profanity from chat", true));

    public ChatFilter() {
        super("ChatFilter", "Filter unwanted chat messages", Category.MISC);
    }

    @EventHandler
    public void onTick(EventTick event) {
        // Filtering is applied via mixin on ChatHud.addMessage
    }

    public boolean shouldFilter(String message) {
        if (!profanity.getValue()) return false;
        String lower = message.toLowerCase();
        // Placeholder: A full implementation would use a configurable word list
        return false;
    }
}
