package dev.y0.client.module.misc;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class NameProtect extends Module {
    private final BooleanSetting tabList = addSetting(new BooleanSetting("Tab List", "Hide your name in the tab list", true));
    private final BooleanSetting chat = addSetting(new BooleanSetting("Chat", "Hide your name in chat messages", true));

    private static final String PROTECTED_NAME = "Protected";

    public NameProtect() {
        super("NameProtect", "Hide your name in chat and tab list", Category.MISC);
    }

    // NOTE: This module requires mixins to function:
    //
    // 1. Tab list protection: Mixin on PlayerListHud.render() or
    //    PlayerListEntry.getDisplayName() to replace the player's name
    //    with the protected name in the tab list rendering.
    //
    // 2. Chat protection: Mixin on ChatHud.addMessage() or
    //    Text rendering methods to replace occurrences of the player's
    //    real name with the protected name in incoming chat messages.
    //
    // 3. Optionally, mixin on TextRenderer.draw() to globally replace
    //    the name in all rendered text.

    /**
     * Returns the replacement name used to hide the real player name.
     *
     * @return the protected display name
     */
    public String getProtectedName() {
        return PROTECTED_NAME;
    }

    /**
     * Checks if the given text contains the player's name and replaces it.
     *
     * @param text the original text
     * @return the text with the player's name replaced, or the original text
     */
    public String replaceName(String text) {
        if (mc.player == null) return text;

        String realName = mc.player.getName().getString();
        if (text.contains(realName)) {
            return text.replace(realName, PROTECTED_NAME);
        }
        return text;
    }

    public boolean shouldProtectTabList() {
        return isEnabled() && tabList.getValue();
    }

    public boolean shouldProtectChat() {
        return isEnabled() && chat.getValue();
    }
}
