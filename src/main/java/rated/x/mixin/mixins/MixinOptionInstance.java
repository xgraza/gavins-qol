package rated.x.mixin.mixins;

import net.minecraft.client.OptionInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import rated.x.mixin.duck.IOptionInstance;

/**
 * @param <T>
 * @author xgraza
 * @since 1.0.0
 */
@Mixin(OptionInstance.class)
public final class MixinOptionInstance<T> implements IOptionInstance<T>
{
    @Shadow
    @Mutable
    T value;

    @Override
    public void hook_ratedX$setValue(T value)
    {
        this.value = value;
    }
}
