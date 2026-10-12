package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class CommandNotify extends Module {
    private final BooleanSetting sound = addSetting(new BooleanSetting("Sound", "Play a sound on command execution", true));

    public CommandNotify() {
        super("CommandNotify", "Notify when commands are executed nearby", Category.MISC);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Command detection handled by mixin on incoming chat packets
        // that match command feedback patterns.
    }

    public boolean shouldPlaySound() {
        return sound.getValue();
    }
}
