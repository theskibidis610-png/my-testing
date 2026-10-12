package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;

public class AutoBlock extends Module {
    private final BooleanSetting swordOnly = addSetting(new BooleanSetting("Sword Only", "Only block with swords", true));

    public AutoBlock() {
        super("AutoBlock", "Auto block after hitting", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (swordOnly.getValue() && !(mc.player.getMainHandStack().getItem() instanceof SwordItem)) return;
        if (mc.player.getAttacking() != null) {
            mc.options.useKey.setPressed(true);
        }
    }
}
