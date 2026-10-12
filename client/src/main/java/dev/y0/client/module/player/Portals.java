package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class Portals extends Module {
    private final BooleanSetting noOverlay = addSetting(new BooleanSetting("No Overlay", "Remove portal overlay effect", true));
    private final BooleanSetting noNausea = addSetting(new BooleanSetting("No Nausea", "Remove nausea effect from portals", true));

    public Portals() {
        super("Portals", "Remove portal overlay and effects", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        // Remove nausea status effect caused by portals
        if (noNausea.getValue()) {
            // Note: Requires a mixin on InGameHud.renderPortalOverlay to cancel rendering
            // when this module is enabled. Also a mixin on Entity.getPortalCooldown
            // or LivingEntity to suppress the NAUSEA effect.
            mc.player.removeStatusEffect(net.minecraft.entity.effect.StatusEffects.NAUSEA);
        }

        // Note: The overlay removal requires a mixin on:
        // - InGameHud.renderPortalOverlay: return early when noOverlay is true
        // - InGameOverlayRenderer.renderPortalOverlay: cancel rendering
        // The module toggle and framework are ready; mixin integration is needed
        // for the visual overlay suppression to work.
    }
}
