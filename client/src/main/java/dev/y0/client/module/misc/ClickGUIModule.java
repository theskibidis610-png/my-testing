package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import org.lwjgl.glfw.GLFW;

public class ClickGUIModule extends Module {

    public ClickGUIModule() {
        super("ClickGUI", "Open the module menu", Category.MISC, GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    @Override
    protected void onEnable() {
        if (mc.currentScreen == null) {
            mc.setScreen(Y0Client.getInstance().getClickGuiScreen());
        }
        setEnabled(false);
    }
}
