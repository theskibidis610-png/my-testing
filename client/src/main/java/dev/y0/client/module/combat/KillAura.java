package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;
import dev.y0.client.setting.ModeSetting;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import org.lwjgl.glfw.GLFW;

public class KillAura extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Attack range", 3.5, 1.0, 6.0, 0.1));
    private final NumberSetting cps = addSetting(new NumberSetting("CPS", "Clicks per second", 12.0, 1.0, 20.0, 1.0));
    private final ModeSetting priority = addSetting(new ModeSetting("Priority", "Target priority", "Distance", "Distance", "Health", "Angle"));
    private final BooleanSetting playersOnly = addSetting(new BooleanSetting("Players Only", "Only target players", true));
    private final BooleanSetting autoBlock = addSetting(new BooleanSetting("AutoBlock", "Block between hits", false));

    private long lastAttackTime;
    private Entity target;

    public KillAura() {
        super("KillAura", "Automatically attacks nearby entities", Category.COMBAT, GLFW.GLFW_KEY_R);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        target = findTarget();
        if (target == null) return;

        long delay = (long) (1000.0 / cps.getValue());
        long now = System.currentTimeMillis();

        if (now - lastAttackTime >= delay) {
            mc.interactionManager.attackEntity(mc.player, target);
            mc.player.swingHand(Hand.MAIN_HAND);
            lastAttackTime = now;
        }
    }

    private Entity findTarget() {
        Entity best = null;
        double bestScore = Double.MAX_VALUE;

        for (Entity entity : mc.world.getEntities()) {
            if (entity == mc.player) continue;
            if (!(entity instanceof LivingEntity living)) continue;
            if (living.isDead() || living.getHealth() <= 0) continue;
            if (playersOnly.getValue() && !(entity instanceof PlayerEntity)) continue;

            double dist = mc.player.distanceTo(entity);
            if (dist > range.getValue()) continue;

            double score = switch (priority.getValue()) {
                case "Health" -> living.getHealth();
                case "Angle" -> {
                    double diffX = entity.getX() - mc.player.getX();
                    double diffZ = entity.getZ() - mc.player.getZ();
                    float yaw = (float) Math.toDegrees(Math.atan2(diffZ, diffX)) - 90;
                    yield Math.abs(mc.player.getYaw() - yaw);
                }
                default -> dist;
            };

            if (score < bestScore) {
                bestScore = score;
                best = entity;
            }
        }

        return best;
    }

    @Override
    protected void onDisable() {
        target = null;
    }
}
