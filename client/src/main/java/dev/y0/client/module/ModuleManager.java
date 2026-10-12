package dev.y0.client.module;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventKeyPress;
import dev.y0.client.module.combat.*;
import dev.y0.client.module.misc.*;
import dev.y0.client.module.movement.*;
import dev.y0.client.module.player.*;
import dev.y0.client.module.render.*;
import dev.y0.client.module.world.*;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public void init() {
        // Combat
        add(new KillAura());
        add(new Velocity());
        add(new Criticals());
        add(new AutoTotem());
        add(new Reach());
        add(new AutoCrystal());

        // Movement
        add(new Sprint());
        add(new Speed());
        add(new Fly());
        add(new Scaffold());
        add(new NoSlow());
        add(new Step());
        add(new Jesus());
        add(new HighJump());

        // Render
        add(new Fullbright());
        add(new ESP());
        add(new Tracers());
        add(new HUD());
        add(new StorageESP());
        add(new NoRender());

        // Player
        add(new NoFall());
        add(new AutoArmor());
        add(new ChestStealer());
        add(new FastPlace());
        add(new FastBreak());
        add(new AntiHunger());

        // World
        add(new Timer());
        add(new Nuker());

        // Misc
        add(new AutoFish());
        add(new ClickGUIModule());
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
