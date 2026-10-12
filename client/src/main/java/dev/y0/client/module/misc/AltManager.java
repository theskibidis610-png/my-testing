package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class AltManager extends Module {

    public AltManager() {
        super("AltManager", "Alt account manager placeholder", Category.MISC);
    }

    @Override
    protected void onEnable() {
        // Placeholder: A full implementation would open an alt account GUI
        // with login capabilities for Microsoft/offline accounts.
        Y0Client.LOGGER.info("AltManager enabled - would open alt manager GUI");
        setEnabled(false);
    }
}
