package dev.y0.client.module.combat;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class ExplodeBow extends Module {
    private final NumberSetting power = addSetting(new NumberSetting("Power", "Arrow power multiplier", 3.0, 1.0, 10.0, 1.0));

    public ExplodeBow() {
        super("ExplodeBow", "Exploding arrow exploit", Category.COMBAT);
    }

    @EventHandler
    public void onTick(EventTick event) {
        if (mc.player == null) return;
        if (mc.player.getMainHandStack().getItem() != Items.BOW) return;
        if (mc.player.getItemUseTime() >= 3) {
            for (int i = 0; i < (int) power.getValue(); i++) {
                mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(
                        mc.player.getX(), mc.player.getY() - 1e-10, mc.player.getZ(),
                        true, mc.player.horizontalCollision));
            }
            mc.interactionManager.stopUsingItem(mc.player);
        }
    }
}
