package rated.x.mixin.duck;

/**
 * @author xgraza
 * @since 1.0.0
 */
public interface IOptionInstance<T>
{
    void hook_ratedX$setValue(final T value);
}
