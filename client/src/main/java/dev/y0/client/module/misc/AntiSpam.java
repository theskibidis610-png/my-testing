package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

import java.util.LinkedList;

public class AntiSpam extends Module {
    private final NumberSetting threshold = addSetting(new NumberSetting("Threshold", "Max duplicate messages before filtering", 3.0, 1.0, 10.0, 1.0));

    private final LinkedList<String> recentMessages = new LinkedList<>();

    public AntiSpam() {
        super("AntiSpam", "Filter repeated spam messages in chat", Category.MISC);
    }

    @Override
    protected void onEnable() {
        recentMessages.clear();
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Spam filtering is handled by mixin on ChatHud.addMessage;
        // recentMessages tracks the last N messages for duplicate detection.
    }

    public boolean shouldFilter(String message) {
        long count = recentMessages.stream().filter(m -> m.equals(message)).count();
        if (count >= (int) threshold.getValue()) return true;
        recentMessages.addLast(message);
        if (recentMessages.size() > 50) recentMessages.removeFirst();
        return false;
    }
}
