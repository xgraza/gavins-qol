/*
 * Copyright (c) xgraza 2025
 */

package rated.x.mixin.mixins;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.ToastManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rated.x.RatedX;
import rated.x.listener.event.EventRenderToasts;

@Mixin(ToastManager.class)
public final class MixinToastManager
{
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void hook_ratedX$render(final GuiGraphics graphics, final CallbackInfo info)
    {
        if (RatedX.EVENT_BUS.dispatch(new EventRenderToasts()))
        {
            info.cancel();
        }
    }
}
