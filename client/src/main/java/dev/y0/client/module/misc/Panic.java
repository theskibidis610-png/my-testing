package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class Panic extends Module {

    public Panic() {
        super("Panic", "Disable all modules at once", Category.MISC);
    }

    @Override
    protected void onEnable() {
        int disabledCount = 0;

        // Iterate through all registered modules and disable them
        for (Module module : Y0Client.getInstance().getModuleManager().getModules()) {
            // Skip this module to avoid infinite recursion
            if (module == this) continue;

            if (module.isEnabled()) {
                module.disable();
                disabledCount++;
            }
        }

        Y0Client.LOGGER.info("Panic: Disabled {} modules", disabledCount);

        // Disable self after disabling everything else
        setEnabled(false);
    }
}
