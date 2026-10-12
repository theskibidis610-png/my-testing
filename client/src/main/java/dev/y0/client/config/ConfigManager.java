package dev.y0.client.config;

import com.google.gson.*;
import dev.y0.client.Y0Client;
import dev.y0.client.module.Module;
import dev.y0.client.setting.Setting;
import net.minecraft.client.MinecraftClient;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {
    private final Path configDir;
    private final Path modulesFile;
    private final Gson gson;

    public ConfigManager() {
        configDir = MinecraftClient.getInstance().runDirectory.toPath().resolve("y0");
        modulesFile = configDir.resolve("modules.json");
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void save() {
        try {
            Files.createDirectories(configDir);

            JsonObject root = new JsonObject();
            for (Module module : Y0Client.getInstance().getModuleManager().getModules()) {
                JsonObject moduleObj = new JsonObject();
                moduleObj.addProperty("enabled", module.isEnabled());
                moduleObj.addProperty("keyBind", module.getKeyBind());

                JsonObject settingsObj = new JsonObject();
                for (Setting<?> setting : module.getSettings()) {
                    settingsObj.add(setting.getName(), setting.serialize());
                }
                moduleObj.add("settings", settingsObj);

                root.add(module.getName(), moduleObj);
            }

            try (Writer writer = Files.newBufferedWriter(modulesFile)) {
                gson.toJson(root, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        if (!Files.exists(modulesFile)) return;

        try (Reader reader = Files.newBufferedReader(modulesFile)) {
            JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();

            for (Module module : Y0Client.getInstance().getModuleManager().getModules()) {
                if (!root.has(module.getName())) continue;

                JsonObject moduleObj = root.getAsJsonObject(module.getName());

                if (moduleObj.has("keyBind")) {
                    module.setKeyBind(moduleObj.get("keyBind").getAsInt());
                }

                if (moduleObj.has("settings")) {
                    JsonObject settingsObj = moduleObj.getAsJsonObject("settings");
                    for (Setting<?> setting : module.getSettings()) {
                        if (settingsObj.has(setting.getName())) {
                            setting.deserialize(settingsObj.get(setting.getName()));
                        }
                    }
                }

                if (moduleObj.has("enabled") && moduleObj.get("enabled").getAsBoolean()) {
                    module.enable();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
