package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.entity.player.PlayerAbilities;

public class FakeCreative extends Module {
    private final BooleanSetting instantBreak = addSetting(new BooleanSetting("Instant Break", "Enable instant block breaking", true));
    private final BooleanSetting fly = addSetting(new BooleanSetting("Fly", "Enable creative-like flight", false));

    public FakeCreative() {
        super("FakeCreative", "Simulates creative-like features in survival", Category.PLAYER);
    }

    @Override
    protected void onDisable() {
        if (mc.player != null) {
            PlayerAbilities abilities = mc.player.getAbilities();
            abilities.allowFlying = false;
            abilities.flying = false;
            abilities.creativeMode = false;
        }
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        PlayerAbilities abilities = mc.player.getAbilities();

        if (fly.getValue()) {
            abilities.allowFlying = true;
        }

        if (instantBreak.getValue()) {
            abilities.creativeMode = true;
        }
    }
}
