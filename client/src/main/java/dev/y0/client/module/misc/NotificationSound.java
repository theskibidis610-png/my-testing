package dev.y0.client.module.misc;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;

public class NotificationSound extends Module {
    private final ModeSetting sound = addSetting(new ModeSetting("Sound", "Notification sound type", "Ding", "Ding", "Pop", "None"));

    public NotificationSound() {
        super("NotificationSound", "Custom notification sounds for events", Category.MISC);
    }

    public String getSelectedSound() {
        return sound.getValue();
    }

    public boolean isMuted() {
        return "None".equals(sound.getValue());
    }
}
