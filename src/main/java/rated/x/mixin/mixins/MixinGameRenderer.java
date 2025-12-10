package rated.x.mixin.mixins;

import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rated.x.RatedX;

/**
 * @author xgraza
 * @since 1.0.0
 */
@Mixin(GameRenderer.class)
public final class MixinGameRenderer
{
    @Unique
    private static final float MAX_DEPTH_FAR = 30000000.0f;

    @Inject(method = "getDepthFar", at = @At("HEAD"), cancellable = true)
    private void hook_ratedX$getDepthFar(final CallbackInfoReturnable<Float> info)
    {
        if (RatedX.INSTANCE.getWaypointManager().isRenderEnabled())
        {
            info.setReturnValue(MAX_DEPTH_FAR);
        }
    }
}
