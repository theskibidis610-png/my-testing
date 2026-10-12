package dev.y0.client.module.misc;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.NumberSetting;

public class NoSoundLag extends Module {
    private final NumberSetting maxSounds = addSetting(new NumberSetting("Max Sounds", "Maximum concurrent sounds allowed", 100.0, 50.0, 500.0, 10.0));

    public NoSoundLag() {
        super("NoSoundLag", "Prevent lag from excessive sounds", Category.MISC);
    }

    // NOTE: This module requires a mixin on net.minecraft.client.sound.SoundSystem
    // to limit the number of concurrent sound instances. When the count exceeds
    // getMaxSounds(), new sounds are dropped instead of being queued.
    //
    // @Inject(method = "play(Lnet/minecraft/client/sound/SoundInstance;)V",
    //         at = @At("HEAD"), cancellable = true)
    // The mixin checks the active sound count and cancels if over the limit.

    public int getMaxSounds() {
        return (int) maxSounds.getValue();
    }
}
