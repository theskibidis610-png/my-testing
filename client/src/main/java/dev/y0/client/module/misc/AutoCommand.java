package dev.y0.client.module.misc;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class AutoCommand extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Ticks to wait after join before executing", 40.0, 10.0, 200.0, 1.0));

    private int ticksSinceJoin;
    private boolean commandSent;

    // Command to run on join. A full implementation would use a StringSetting.
    private static final String COMMAND = "spawn";

    public AutoCommand() {
        super("AutoCommand", "Automatically run a command on server join", Category.MISC);
    }

    @Override
    protected void onEnable() {
        ticksSinceJoin = 0;
        commandSent = false;
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.getNetworkHandler() == null) return;
        if (commandSent) return;

        ticksSinceJoin++;
        if (ticksSinceJoin >= (int) delay.getValue()) {
            mc.player.networkHandler.sendChatCommand(COMMAND);
            commandSent = true;
        }
    }
}
