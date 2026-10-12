package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import net.minecraft.entity.mob.Monster;
import net.minecraft.item.SwordItem;
import net.minecraft.item.AxeItem;

public class WeaponSwitch extends Module {
    private final ModeSetting situation = addSetting(new ModeSetting("Situation", "Switch mode", "Auto", "PVP", "PVE", "Auto"));

    public WeaponSwitch() {
        super("WeaponSwitch", "Auto switch weapons", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;
        boolean pve = situation.getValue().equals("PVE") || (situation.getValue().equals("Auto") &&
                mc.world.getEntitiesByClass(Monster.class, mc.player.getBoundingBox().expand(6), e -> true).size() > 0);
        Class<?> target = pve ? AxeItem.class : SwordItem.class;
        for (int i = 0; i < 9; i++) {
            if (target.isInstance(mc.player.getInventory().getStack(i).getItem())) {
                mc.player.getInventory().selectedSlot = i;
                return;
            }
        }
    }
}
