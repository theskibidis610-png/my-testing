package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class AntiChatClear extends Module {

    public AntiChatClear() {
        super("AntiChatClear", "Prevent servers from clearing your chat", Category.MISC);
    }

    @Override
    protected void onEnable() {
        // NOTE: Requires a mixin on ChatHud.clear() to cancel when this module is active.
        // The mixin should check if this module is enabled and skip the clear call.
        Y0Client.LOGGER.info("AntiChatClear enabled - chat clear packets will be ignored");
    }
}
