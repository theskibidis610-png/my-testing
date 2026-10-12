package dev.y0.client.module.misc;

import dev.y0.client.Y0Client;
import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.BooleanSetting;

public class MusicPlayer extends Module {
    private final BooleanSetting noteblocks = addSetting(new BooleanSetting("Noteblocks", "Use noteblock sounds for playback", false));

    public MusicPlayer() {
        super("MusicPlayer", "Custom in-game music player", Category.MISC);
    }

    @Override
    protected void onEnable() {
        // Placeholder: Would load .nbs files or MIDI and play them
        // using noteblock sounds or the Minecraft sound engine.
        Y0Client.LOGGER.info("MusicPlayer enabled - noteblocks: {}", noteblocks.getValue());
    }

    @Override
    protected void onDisable() {
        Y0Client.LOGGER.info("MusicPlayer stopped");
    }

    public boolean useNoteblocks() {
        return noteblocks.getValue();
    }
}
