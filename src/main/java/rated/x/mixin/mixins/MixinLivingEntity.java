/*
 * Copyright (c) xgraza 2025
 */

package rated.x.mixin.mixins;

import net.minecraft.world.entity.EntityEquipment;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import rated.x.mixin.duck.ILivingEntity;

/**
 * @author xgraza
 * @since 1.0.0
 */
@Mixin(LivingEntity.class)
public final class MixinLivingEntity implements ILivingEntity
{
    @Shadow
    @Final
    protected EntityEquipment equipment;

    @Override
    public EntityEquipment hook_ratedX$equipment()
    {
        return equipment;
    }
}
