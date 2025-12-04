/*
 * Copyright (c) xgraza 2025
 */

package rated.x.mixin.mixins;

import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rated.x.RatedX;
import rated.x.listener.event.EventTick;

/**
 * @author xgraza
 * @since 1.0.0
 */
@Mixin(LocalPlayer.class)
public final class MixinLocalPlayer
{
    @Inject(method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/player/AbstractClientPlayer;tick()V"))
    private void hook_ratedX$tick(final CallbackInfo info)
    {
        RatedX.EVENT_BUS.dispatch(new EventTick());
    }
}
