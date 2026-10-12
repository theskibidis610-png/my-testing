package dev.y0.client.module.render;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class TabCustom extends Module {
    private final BooleanSetting ping = addSetting(new BooleanSetting("Ping", "Show numeric ping values", true));
    private final BooleanSetting header = addSetting(new BooleanSetting("Header", "Show custom header", true));

    public TabCustom() {
        super("TabCustom", "Custom player tab list display", Category.RENDER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        // Tab list modifications handled via PlayerListHud mixin
    }

    public boolean showPing() { return ping.getValue(); }
    public boolean showHeader() { return header.getValue(); }
}
