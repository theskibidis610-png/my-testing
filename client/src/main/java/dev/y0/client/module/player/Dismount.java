package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class Dismount extends Module {
    private final BooleanSetting onDamage = addSetting(new BooleanSetting("OnDamage", "Dismount when taking damage", true));
    private float lastHealth;

    public Dismount() {
        super("Dismount", "Automatically dismounts from vehicles", Category.PLAYER);
    }

    @Override
    protected void onEnable() {
        if (mc.player != null) lastHealth = mc.player.getHealth();
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || !mc.player.hasVehicle()) return;

        if (onDamage.getValue() && mc.player.getHealth() < lastHealth) {
            mc.player.dismountVehicle();
        }
        lastHealth = mc.player.getHealth();
    }
}
