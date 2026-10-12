package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

import java.util.ArrayList;
import java.util.List;

public class MCF extends Module {
    private final ModeSetting action = addSetting(new ModeSetting("Action", "What to do on middle click", "Friend", "Friend", "Enemy"));

    private final List<String> friends = new ArrayList<>();
    private final List<String> enemies = new ArrayList<>();
    private boolean wasPressed;

    public MCF() {
        super("MCF", "Middle click to add friends or enemies", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.mouse == null) return;

        boolean pressed = mc.mouse.wasMiddleButtonClicked();
        if (pressed && !wasPressed && mc.crosshairTarget instanceof EntityHitResult hit) {
            Entity entity = hit.getEntity();
            if (entity instanceof PlayerEntity target) {
                String name = target.getGameProfile().getName();
                if (action.getValue().equals("Friend")) {
                    if (friends.contains(name)) {
                        friends.remove(name);
                        mc.player.sendMessage(Text.literal("Removed friend: " + name), false);
                    } else {
                        friends.add(name);
                        mc.player.sendMessage(Text.literal("Added friend: " + name), false);
                    }
                } else {
                    if (enemies.contains(name)) {
                        enemies.remove(name);
                        mc.player.sendMessage(Text.literal("Removed enemy: " + name), false);
                    } else {
                        enemies.add(name);
                        mc.player.sendMessage(Text.literal("Added enemy: " + name), false);
                    }
                }
            }
        }
        wasPressed = pressed;
    }

    public boolean isFriend(String name) { return friends.contains(name); }
    public boolean isEnemy(String name) { return enemies.contains(name); }
}
