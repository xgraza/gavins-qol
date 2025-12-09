package rated.x.mixin.mixins;

import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import rated.x.module.impl.player.ModuleFreeLook;

/**
 * @author xgraza
 * @since 1.0.0
 */
@Mixin(Camera.class)
public final class MixinCamera
{
    @ModifyVariable(method = "setRotation", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private float hook_ratedX$setRotation$modify_x(float x)
    {
        if (ModuleFreeLook.INSTANCE.isOverrideRotate())
        {
            return ModuleFreeLook.INSTANCE.getX();
        }
        return x;
    }

    @ModifyVariable(method = "setRotation", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private float hook_ratedX$setRotation$modify_y(float y)
    {
        if (ModuleFreeLook.INSTANCE.isOverrideRotate())
        {
            return ModuleFreeLook.INSTANCE.getY();
        }
        return y;
    }
}
