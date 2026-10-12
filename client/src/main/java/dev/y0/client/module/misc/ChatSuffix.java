package dev.y0.client.module.misc;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;

public class ChatSuffix extends Module {
    private final ModeSetting suffixType = addSetting(new ModeSetting("Suffix", "Type of suffix to append", "Client", "Client", "Custom"));

    // Default suffix strings
    private static final String CLIENT_SUFFIX = " | Y0";
    private static final String CUSTOM_SUFFIX = " | Custom";

    public ChatSuffix() {
        super("ChatSuffix", "Add a suffix to chat messages", Category.MISC);
    }

    // NOTE: This module requires a mixin on ClientPlayerEntity.sendChatMessage()
    // or on ClientPlayNetworkHandler to intercept outgoing chat messages
    // and append the configured suffix before they are sent to the server.
    //
    // Example mixin target: net.minecraft.client.network.ClientPlayerEntity
    // @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    // The mixin would call getSuffix() and append it to the message string.

    /**
     * Returns the current suffix string based on the selected mode.
     *
     * @return the suffix to append to chat messages
     */
    public String getSuffix() {
        return switch (suffixType.getValue()) {
            case "Client" -> CLIENT_SUFFIX;
            case "Custom" -> CUSTOM_SUFFIX;
            default -> "";
        };
    }
}
