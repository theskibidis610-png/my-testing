package dev.y0.client.module.movement;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;

public class Clip extends Module {
    private final ModeSetting direction = addSetting(new ModeSetting("Direction", "Clip direction", "Up", "Up", "Down"));

    public Clip() {
        super("Clip", "Clip through blocks vertically", Category.MOVEMENT);
    }

    @Override
    protected void onEnable() {
        if (mc.player == null) {
            disable();
            return;
        }

        double offset = direction.getValue().equals("Up") ? 1.0 : -1.0;
        mc.player.setPosition(mc.player.getX(), mc.player.getY() + offset, mc.player.getZ());
        disable();
    }
}
