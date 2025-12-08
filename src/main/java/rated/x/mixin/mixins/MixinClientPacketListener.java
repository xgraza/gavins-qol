package rated.x.mixin.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundDamageEventPacket;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rated.x.RatedX;
import rated.x.listener.event.EventPlayerHurt;

/**
 * @author xgraza
 * @since 1.0.0
 */
@Mixin(ClientPacketListener.class)
public final class MixinClientPacketListener
{
    @Shadow
    private ClientLevel level;

    @Inject(method = "handleDamageEvent",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;handleDamageEvent(Lnet/minecraft/world/damagesource/DamageSource;)V"))
    private void hook_ratedX$handleDamageEvent(final ClientboundDamageEventPacket packet,
                                               final CallbackInfo info)
    {
        final Entity entity = level.getEntity(packet.entityId());
        if (entity != null && entity.equals(Minecraft.getInstance().player))
        {
            final DamageSource damageSource = packet.getSource(level);
            RatedX.EVENT_BUS.dispatch(new EventPlayerHurt(damageSource));
        }
    }
}
