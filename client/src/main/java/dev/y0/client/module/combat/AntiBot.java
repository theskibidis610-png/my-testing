package dev.y0.client.module.combat;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import dev.y0.client.setting.ModeSetting;
import net.minecraft.entity.player.PlayerEntity;

public class AntiBot extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Detection method", "Advanced", "Advanced", "Tab", "Health"));
    private final BooleanSetting remove = addSetting(new BooleanSetting("Remove", "Remove bots from world", false));

    public AntiBot() {
        super("AntiBot", "Filter out bot entities", Category.COMBAT);
    }

    public boolean isBot(PlayerEntity player) {
        if (!isEnabled()) return false;
        return switch (mode.getValue()) {
            case "Tab" -> mc.getNetworkHandler() != null && mc.getNetworkHandler().getPlayerListEntry(player.getUuid()) == null;
            case "Health" -> player.getHealth() != 20f || player.getAbsorptionAmount() > 0;
            default -> player.getGameProfile().getName().length() > 16 || !player.canSee(mc.player);
        };
    }
}
