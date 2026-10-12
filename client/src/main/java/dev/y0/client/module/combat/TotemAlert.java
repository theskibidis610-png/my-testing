package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.sound.SoundEvents;

public class TotemAlert extends Module {
    private final BooleanSetting sound = addSetting(new BooleanSetting("Sound", "Play alert sound", true));
    private boolean alerted;

    public TotemAlert() {
        super("TotemAlert", "Alert when no totem", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        boolean hasTotems = mc.player.getOffHandStack().getItem() == Items.TOTEM_OF_UNDYING;
        if (!hasTotems && !alerted) {
            mc.player.sendMessage(Text.literal("[Y0] No totem in offhand!"), true);
            if (sound.getValue()) {
                mc.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING.value(), 1.0f, 1.0f);
            }
            alerted = true;
        } else if (hasTotems) {
            alerted = false;
        }
    }
}
