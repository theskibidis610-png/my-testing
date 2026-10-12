package dev.y0.client;

import dev.y0.client.config.ConfigManager;
import dev.y0.client.event.EventBus;
import dev.y0.client.gui.LicenseScreen;
import dev.y0.client.gui.clickgui.ClickGuiScreen;
import dev.y0.client.license.LicenseManager;
import dev.y0.client.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class Y0Client implements ClientModInitializer {
    public static final String NAME = "Y0";
    public static final String VERSION = "0.1.0";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    private static Y0Client instance;

    private LicenseManager licenseManager;
    private EventBus eventBus;
    private ModuleManager moduleManager;
    private ConfigManager configManager;
    private ClickGuiScreen clickGuiScreen;

    @Override
    public void onInitializeClient() {
        instance = this;
        LOGGER.info("Loading {} v{}", NAME, VERSION);

        Path dataDir = FabricLoader.getInstance().getGameDir().resolve("y0");
        licenseManager = new LicenseManager(dataDir);
        licenseManager.loadAndValidate();

        eventBus = new EventBus();
        moduleManager = new ModuleManager();
        moduleManager.init();
        eventBus.register(moduleManager);

        configManager = new ConfigManager();
        configManager.load();

        clickGuiScreen = new ClickGuiScreen();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!licenseManager.isValid()) {
                if (client.getWindow() != null && !(client.currentScreen instanceof LicenseScreen)) {
                    client.setScreen(new LicenseScreen());
                }
                return;
            }

            if (client.currentScreen == null
                    && client.getWindow() != null
                    && GLFW.glfwGetKey(client.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS) {
                client.setScreen(clickGuiScreen);
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread(() -> configManager.save()));

        LOGGER.info("{} loaded successfully!", NAME);
    }

    public static Y0Client getInstance() {
        return instance;
    }

    public LicenseManager getLicenseManager() { return licenseManager; }
    public EventBus getEventBus() { return eventBus; }
    public ModuleManager getModuleManager() { return moduleManager; }
    public ConfigManager getConfigManager() { return configManager; }
    public ClickGuiScreen getClickGuiScreen() { return clickGuiScreen; }
}
