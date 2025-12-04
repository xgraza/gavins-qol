/*
 * Copyright (c) xgraza 2025
 */

package rated.x.mixin.mixins;

import net.minecraft.client.renderer.LightTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import rated.x.RatedX;
import rated.x.listener.event.EventGamma;

/**
 * @author xgraza
 * @since 1.0.0
 */
@Mixin(LightTexture.class)
public final class MixinLightTexture
{
    @Redirect(method = "updateLightTexture",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/Double;floatValue()F"))
    private float redirect_ratedX$updateLightTexture(final Double gammaInstance)
    {
        final float gamma = gammaInstance.floatValue();
        final EventGamma event = new EventGamma(gamma);
        if (RatedX.EVENT_BUS.dispatch(event))
        {
            return event.getGamma();
        }
        return gamma;
    }
}
