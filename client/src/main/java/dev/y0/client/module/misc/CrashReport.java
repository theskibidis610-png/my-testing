package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class CrashReport extends Module {
    private final BooleanSetting autoRestart = addSetting(new BooleanSetting("Auto Restart", "Automatically restart after a crash", false));

    public CrashReport() {
        super("CrashReport", "Customize crash report behavior", Category.MISC);
    }

    @Override
    protected void onEnable() {
        // NOTE: Requires a mixin on CrashReport to add Y0 client info
        // and optionally trigger an auto-restart via the launch script.
        Y0Client.LOGGER.info("CrashReport customization enabled");
    }

    public boolean shouldAutoRestart() {
        return autoRestart.getValue();
    }
}
