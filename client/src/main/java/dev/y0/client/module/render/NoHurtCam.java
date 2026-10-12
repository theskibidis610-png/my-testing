package dev.y0.client.module.render;

import dev.y0.client.module.Category;
import dev.y0.client.module.Module;

public class NoHurtCam extends Module {

    public NoHurtCam() {
        super("NoHurtCam", "Remove hurt camera shake", Category.RENDER);
    }

    // Applied via mixin: cancel the hurt camera tilt when this module is enabled
}
