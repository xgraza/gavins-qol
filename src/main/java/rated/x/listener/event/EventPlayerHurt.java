package rated.x.listener.event;

import net.minecraft.world.damagesource.DamageSource;

/**
 * @param damageSource
 * @author xgraza
 * @since 1.0.0
 */
public record EventPlayerHurt(DamageSource damageSource)
{
}
