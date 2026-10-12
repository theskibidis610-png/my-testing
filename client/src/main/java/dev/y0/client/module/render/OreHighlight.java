package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class OreHighlight extends Module {
    private final BooleanSetting diamonds = addSetting(new BooleanSetting("Diamonds", "Highlight diamond ore", true));
    private final BooleanSetting gold = addSetting(new BooleanSetting("Gold", "Highlight gold ore", true));

    public OreHighlight() {
        super("OreHighlight", "Highlight ores through walls", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.world == null) return;
        // Scan loaded chunks for ore blocks and render outlines
    }

    public boolean showDiamonds() { return diamonds.getValue(); }
    public boolean showGold() { return gold.getValue(); }
}
