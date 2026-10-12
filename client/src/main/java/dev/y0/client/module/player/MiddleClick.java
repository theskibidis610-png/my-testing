package dev.y0.client.module.player;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.lwjgl.glfw.GLFW;

public class MiddleClick extends Module {
    private final ModeSetting action = addSetting(new ModeSetting("Action", "What to do on middle click", "Pearl", "Friend", "Pearl"));

    private boolean wasMiddlePressed;

    public MiddleClick() {
        super("MiddleClick", "Middle click actions", Category.PLAYER);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null || mc.world == null) return;

        long handle = mc.getWindow().getHandle();
        boolean middlePressed = GLFW.glfwGetMouseButton(handle, GLFW.GLFW_MOUSE_BUTTON_MIDDLE) == GLFW.GLFW_PRESS;

        if (middlePressed && !wasMiddlePressed) {
            onMiddleClick();
        }

        wasMiddlePressed = middlePressed;
    }

    private void onMiddleClick() {
        if (action.getValue().equals("Friend")) {
            // Add targeted player as a friend
            if (mc.crosshairTarget != null && mc.crosshairTarget.getType() == HitResult.Type.ENTITY) {
                EntityHitResult entityHit = (EntityHitResult) mc.crosshairTarget;
                if (entityHit.getEntity() instanceof PlayerEntity player) {
                    // Friend system integration point: Y0Client.getInstance().getFriendManager().toggleFriend(player.getName().getString());
                    mc.player.sendMessage(net.minecraft.text.Text.literal("Toggled friend: " + player.getName().getString()), true);
                }
            }
        } else if (action.getValue().equals("Pearl")) {
            // Find and throw an ender pearl
            for (int i = 0; i < 9; i++) {
                ItemStack stack = mc.player.getInventory().getStack(i);
                if (stack.getItem() == Items.ENDER_PEARL) {
                    int prevSlot = mc.player.getInventory().selectedSlot;
                    mc.player.getInventory().selectedSlot = i;
                    mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
                    mc.player.getInventory().selectedSlot = prevSlot;
                    return;
                }
            }
        }
    }
}
