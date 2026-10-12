package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class SelfDestruct extends Module {

    public SelfDestruct() {
        super("SelfDestruct", "Remove all traces of the client", Category.MISC);
    }

    @Override
    protected void onEnable() {
        Y0Client.LOGGER.info("SelfDestruct: Removing all client traces");

        // Disable all modules
        for (Module module : Y0Client.getInstance().getModuleManager().getModules()) {
            if (module == this) continue;
            if (module.isEnabled()) {
                module.disable();
            }
        }

        // Clear event bus registrations
        Y0Client.getInstance().getEventBus().unregister(
                Y0Client.getInstance().getModuleManager());

        Y0Client.LOGGER.info("SelfDestruct: All modules disabled and unloaded");

        // Disable self
        setEnabled(false);
    }
}
