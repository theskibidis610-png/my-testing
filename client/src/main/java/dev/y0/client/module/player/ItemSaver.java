package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class ItemSaver extends Module {
    private final NumberSetting durabilityThreshold = addSetting(new NumberSetting("Threshold", "Warn when durability is below this", 10.0, 1.0, 100.0, 1.0));

    private boolean warned;

    public ItemSaver() {
        super("ItemSaver", "Warns when held tool is about to break", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;

        ItemStack held = mc.player.getMainHandStack();
        if (held.isEmpty() || !held.isDamageable()) {
            warned = false;
            return;
        }

        int remaining = held.getMaxDamage() - held.getDamage();
        if (remaining <= (int) durabilityThreshold.getValue()) {
            if (!warned) {
                warned = true;
                mc.player.sendMessage(Text.literal("[ItemSaver] Tool about to break! " + remaining + " durability remaining"), true);
            }
        } else {
            warned = false;
        }
    }
}
