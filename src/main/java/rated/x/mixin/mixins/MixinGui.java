/*
 * Copyright (c) xgraza 2025
 */

package rated.x.mixin.mixins;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rated.x.RatedX;
import rated.x.listener.event.EventRenderHUD;

/**
 * @author xgraza
 * @since 1.0.0
 */
@Mixin(Gui.class)
public final class MixinGui
{
    @Shadow(aliases = "minecraft")
    @Final
    private Minecraft mc;

    @Inject(method = "render", at = @At("TAIL"))
    private void hook_ratedX$render(final GuiGraphics guiGraphics,
                                    final DeltaTracker deltaTracker,
                                    final CallbackInfo info)
    {
        if (mc.options.hideGui)
        {
            return;
        }
        RatedX.EVENT_BUS.dispatch(new EventRenderHUD(guiGraphics, guiGraphics.guiWidth(), guiGraphics.guiWidth()));
    }
}
