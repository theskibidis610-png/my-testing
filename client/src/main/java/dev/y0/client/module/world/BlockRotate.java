package dev.y0.client.module.world;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;
import dev.y0.client.setting.ModeSetting;

/**
 * Forces placed blocks to face a specific direction.
 * Requires a mixin on {@code BlockItem#place} to override the placement
 * state's facing property with the selected direction.
 */
public class BlockRotate extends Module {
    private final ModeSetting facing = addSetting(new ModeSetting("Facing", "Force block facing direction", "North", "North", "South", "East", "West"));

    public BlockRotate() {
        super("BlockRotate", "Force placed block rotation", Category.WORLD);
    }

    public String getSelectedFacing() {
        return facing.getValue();
    }

    // Mixin target: BlockItem#place
    // Override BlockState facing property when isEnabled().
}
