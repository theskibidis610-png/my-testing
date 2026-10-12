package dev.y0.client.module.player;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;

public class GameMode extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Gamemode to switch to", "Survival", "Survival", "Creative", "Adventure"));

    public GameMode() {
        super("GameMode", "Sends a gamemode switch command", Category.PLAYER);
    }

    @Override
    protected void onEnable() {
        if (mc.player == null) {
            disable();
            return;
        }

        String cmd = switch (mode.getValue()) {
            case "Creative" -> "gamemode creative";
            case "Adventure" -> "gamemode adventure";
            default -> "gamemode survival";
        };
        mc.player.networkHandler.sendChatCommand(cmd);
        disable();
    }
}
