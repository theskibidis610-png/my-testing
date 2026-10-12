package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class InvMove extends Module {
    private final BooleanSetting rotate = addSetting(new BooleanSetting("Rotate", "Allow looking around in inventory", true));

    public InvMove() {
        super("InvMove", "Move while inventory is open", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.currentScreen == null) return;
        long handle = mc.getWindow().getHandle();
        KeyBinding.setKeyPressed(mc.options.forwardKey.getDefaultKey(), InputUtil.isKeyPressed(handle, InputUtil.GLFW_KEY_W));
        KeyBinding.setKeyPressed(mc.options.backKey.getDefaultKey(), InputUtil.isKeyPressed(handle, InputUtil.GLFW_KEY_S));
        KeyBinding.setKeyPressed(mc.options.leftKey.getDefaultKey(), InputUtil.isKeyPressed(handle, InputUtil.GLFW_KEY_A));
        KeyBinding.setKeyPressed(mc.options.rightKey.getDefaultKey(), InputUtil.isKeyPressed(handle, InputUtil.GLFW_KEY_D));
        KeyBinding.setKeyPressed(mc.options.jumpKey.getDefaultKey(), InputUtil.isKeyPressed(handle, InputUtil.GLFW_KEY_SPACE));
    }
}
