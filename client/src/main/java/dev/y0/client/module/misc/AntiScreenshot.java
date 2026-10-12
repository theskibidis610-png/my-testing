package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class AntiScreenshot extends Module {

    public AntiScreenshot() {
        super("AntiScreenshot", "Blank out screenshots to protect privacy", Category.MISC);
    }

    // NOTE: This module requires a mixin on net.minecraft.client.util.ScreenshotRecorder
    // to intercept the screenshot saving process.
    //
    // @Inject(method = "saveScreenshot", at = @At("HEAD"), cancellable = true)
    // When this module is enabled, the mixin replaces the framebuffer image
    // with a blank (black) image before saving, preventing any game content
    // from appearing in the screenshot file.

    @Override
    protected void onEnable() {
        Y0Client.LOGGER.info("AntiScreenshot enabled - screenshots will be blanked");
    }
}
