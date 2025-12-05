/*
 * Copyright (c) xgraza 2025
 */

package rated.x.mixin.mixins;

import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.input.KeyEvent;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rated.x.RatedX;
import rated.x.listener.event.EventKeyPress;

/**
 * @author xgraza
 * @since 1.0.0
 */
@Mixin(KeyboardHandler.class)
public final class MixinKeyboardHandler
{
    @Shadow
    @Final
    private Minecraft minecraft;

    @Inject(method = "keyPress", at = @At("HEAD"))
    private void hook_ratedX$keyPress(
            final long handle,
            final int type,
            final KeyEvent event,
            final CallbackInfo info)
    {
        if (handle != minecraft.getWindow().handle() || type != GLFW.GLFW_PRESS)
        {
            return;
        }
        RatedX.EVENT_BUS.dispatch(new EventKeyPress(event.key(),
                event.modifiers(), event.scancode()));
    }
}
