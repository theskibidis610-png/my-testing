package dev.y0.client.module.world;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventTick;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class SpeedMine extends Module {
    private final NumberSetting multiplier = addSetting(new NumberSetting("Multiplier", "Mining speed multiplier", 1.5, 1.0, 3.0, 0.1));

    public SpeedMine() {
        super("SpeedMine", "Mine blocks faster", Category.WORLD);
    }

    // Requires a mixin on AbstractBlock.calcBlockBreakingDelta() or
    // ClientPlayerInteractionManager to multiply the break speed by
    // getMultiplier() when this module is enabled.
    //
    // Example mixin target: AbstractBlockStateMixin
    //   @Inject(method = "calcBlockBreakingDelta", at = @At("RETURN"), cancellable = true)
    //   private void onCalcDelta(PlayerEntity player, BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
    //       SpeedMine speedMine = ModuleManager.get(SpeedMine.class);
    //       if (speedMine != null && speedMine.isEnabled()) {
    //           cir.setReturnValue(cir.getReturnValue() * speedMine.getMultiplier());
    //       }
    //   }

    public float getMultiplier() {
        return (float) multiplier.getValue();
    }

    @EventHandler
    public void onTick(EventTick event) {
        // Tick handler present for event bus registration.
        // The actual speed modification is handled by the mixin above.
    }
}
