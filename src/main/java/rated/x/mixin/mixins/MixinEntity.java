package rated.x.mixin.mixins;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rated.x.RatedX;
import rated.x.listener.event.EventEntityTurn;

/**
 * @author xgraza
 * @since 1.0.0
 */
@Mixin(Entity.class)
public final class MixinEntity
{
    @Inject(method = "turn", at = @At("HEAD"), cancellable = true)
    private void hook_ratedX$turn(final double d, final double e, final CallbackInfo info)
    {
        if (RatedX.EVENT_BUS.dispatch(new EventEntityTurn((Entity) (Object) this, d * 0.15, e * 0.15)))
        {
            info.cancel();
        }
    }
}
