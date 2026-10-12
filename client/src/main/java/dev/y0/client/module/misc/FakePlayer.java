package dev.y0.client.module.misc;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.entity.Entity;

public class FakePlayer extends Module {
    private OtherClientPlayerEntity fakeEntity;
    private static final int FAKE_ENTITY_ID = -100;

    public FakePlayer() {
        super("FakePlayer", "Spawn a fake player entity at your position", Category.MISC);
    }

    @Override
    protected void onEnable() {
        if (mc.player == null || mc.world == null) return;

        // Create a fake player entity that mirrors the local player's profile
        fakeEntity = new OtherClientPlayerEntity(mc.world, mc.player.getGameProfile());
        fakeEntity.setId(FAKE_ENTITY_ID);

        // Copy position and rotation from the real player
        fakeEntity.copyPositionAndRotation(mc.player);
        fakeEntity.setHeadYaw(mc.player.getHeadYaw());
        fakeEntity.setBodyYaw(mc.player.getBodyYaw());

        // Copy inventory/armor appearance
        for (int i = 0; i < mc.player.getInventory().armor.size(); i++) {
            fakeEntity.getInventory().armor.set(i, mc.player.getInventory().armor.get(i).copy());
        }
        fakeEntity.getInventory().main.set(
                mc.player.getInventory().selectedSlot,
                mc.player.getMainHandStack().copy()
        );

        // Add fake entity to the world
        mc.world.addEntity(fakeEntity);
    }

    @Override
    protected void onDisable() {
        if (mc.world == null) return;

        // Remove the fake entity from the world
        if (fakeEntity != null) {
            fakeEntity.remove(Entity.RemovalReason.DISCARDED);
            fakeEntity = null;
        }
    }
}
