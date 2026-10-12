package dev.y0.client.module.render;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class StorageESP extends Module {
    private final BooleanSetting chests = addSetting(new BooleanSetting("Chests", "Highlight chests", true));
    private final BooleanSetting enderChests = addSetting(new BooleanSetting("Ender Chests", "Highlight ender chests", true));
    private final BooleanSetting shulkers = addSetting(new BooleanSetting("Shulkers", "Highlight shulker boxes", true));
    private final BooleanSetting barrels = addSetting(new BooleanSetting("Barrels", "Highlight barrels", false));

    public StorageESP() {
        super("StorageESP", "See storage blocks through walls", Category.RENDER);
    }

    // Requires a WorldRender mixin to draw outlines around storage block entities.
    // Settings and framework are ready.
}
