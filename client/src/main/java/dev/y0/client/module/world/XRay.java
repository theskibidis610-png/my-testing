package dev.y0.client.module.world;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

/**
 * See through blocks to reveal ores and chests.
 * Requires a mixin into {@code Block#shouldDrawSide} or the chunk rendering
 * pipeline to selectively make non-target blocks transparent.
 *
 * Mixin target: Block or AbstractBlock
 * Check: Y0Client.getInstance().getModuleManager().isEnabled(XRay.class)
 * Then call XRay#shouldRender(Block) to decide per-block visibility.
 */
public class XRay extends Module {
    private final BooleanSetting showOres = addSetting(new BooleanSetting("Ores", "Show ore blocks through terrain", true));
    private final BooleanSetting showChests = addSetting(new BooleanSetting("Chests", "Show chests through terrain", true));

    public XRay() {
        super("XRay", "See through blocks to find ores and containers", Category.WORLD);
    }

    @Override
    protected void onEnable() {
        // Force chunk rebuild to apply XRay rendering
        if (mc.worldRenderer != null) {
            mc.worldRenderer.reload();
        }
    }

    @Override
    protected void onDisable() {
        // Rebuild chunks to restore normal rendering
        if (mc.worldRenderer != null) {
            mc.worldRenderer.reload();
        }
    }

    public boolean shouldShowOres() {
        return isEnabled() && showOres.getValue();
    }

    public boolean shouldShowChests() {
        return isEnabled() && showChests.getValue();
    }
}
