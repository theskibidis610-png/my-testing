package dev.y0.client.module.misc;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timestamps extends Module {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Timestamps() {
        super("Timestamps", "Add timestamps to chat messages", Category.MISC);
    }

    // NOTE: This module requires a mixin on net.minecraft.client.gui.hud.ChatHud
    // to prepend the timestamp to each message's Text component.
    //
    // @Inject(method = "addMessage(Lnet/minecraft/text/Text;)V", at = @At("HEAD"))
    // The mixin calls getTimestamp() and prepends it as a gray-colored literal
    // before the original message text.

    public String getTimestamp() {
        return "[" + LocalTime.now().format(FORMAT) + "] ";
    }
}
