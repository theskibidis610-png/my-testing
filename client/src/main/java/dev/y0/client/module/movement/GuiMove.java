package dev.y0.client.module.movement;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class GuiMove extends Module {
    private final BooleanSetting sneak = addSetting(new BooleanSetting("Sneak", "Allow sneaking in GUIs", true));
    private final BooleanSetting jump = addSetting(new BooleanSetting("Jump", "Allow jumping in GUIs", true));

    public GuiMove() {
        super("GuiMove", "Move while GUIs are open", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.currentScreen == null) return;
        if (mc.currentScreen instanceof ChatScreen) return;

        long handle = mc.getWindow().getHandle();
        KeyBinding.setKeyPressed(mc.options.forwardKey.getDefaultKey(), InputUtil.isKeyPressed(handle, InputUtil.GLFW_KEY_W));
        KeyBinding.setKeyPressed(mc.options.backKey.getDefaultKey(), InputUtil.isKeyPressed(handle, InputUtil.GLFW_KEY_S));
        KeyBinding.setKeyPressed(mc.options.leftKey.getDefaultKey(), InputUtil.isKeyPressed(handle, InputUtil.GLFW_KEY_A));
        KeyBinding.setKeyPressed(mc.options.rightKey.getDefaultKey(), InputUtil.isKeyPressed(handle, InputUtil.GLFW_KEY_D));

        if (jump.getValue()) {
            KeyBinding.setKeyPressed(mc.options.jumpKey.getDefaultKey(), InputUtil.isKeyPressed(handle, InputUtil.GLFW_KEY_SPACE));
        }
        if (sneak.getValue()) {
            KeyBinding.setKeyPressed(mc.options.sneakKey.getDefaultKey(), InputUtil.isKeyPressed(handle, InputUtil.GLFW_KEY_LEFT_SHIFT));
        }
    }
}
