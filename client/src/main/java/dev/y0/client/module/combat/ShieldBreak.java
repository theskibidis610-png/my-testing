package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.util.Hand;

public class ShieldBreak extends Module {
    private final BooleanSetting autoSwitch = addSetting(new BooleanSetting("Auto Switch", "Auto switch to axe", true));

    public ShieldBreak() {
        super("ShieldBreak", "Break enemy shields with axe", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        for (PlayerEntity p : mc.world.getPlayers()) {
            if (p == mc.player) continue;
            if (mc.player.distanceTo(p) > 4.5 || !p.isBlocking()) continue;
            if (autoSwitch.getValue()) {
                for (int i = 0; i < 9; i++) {
                    if (mc.player.getInventory().getStack(i).getItem() instanceof AxeItem) {
                        mc.player.getInventory().selectedSlot = i;
                        break;
                    }
                }
            }
            mc.interactionManager.attackEntity(mc.player, p);
            mc.player.swingHand(Hand.MAIN_HAND);
            return;
        }
    }
}
