package dev.y0.client.module;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventKeyPress;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public void init() {
        // Register modules here as you build them:
        // add(new Sprint());
        // add(new Fullbright());
        // add(new KillAura());
    }

    public void add(Module module) {
        modules.add(module);
    }

    @EventHandler
    public void onKeyPress(EventKeyPress event) {
        if (MinecraftClient.getInstance().currentScreen != null) return;

        for (Module module : modules) {
            if (module.getKeyBind() == event.getKey()) {
                module.toggle();
            }
        }
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<Module> getByCategory(Category category) {
        return modules.stream()
                .filter(m -> m.getCategory() == category)
                .collect(Collectors.toList());
    }

    public Module getByName(String name) {
        return modules.stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
