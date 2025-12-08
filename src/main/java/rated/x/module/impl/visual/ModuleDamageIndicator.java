package rated.x.module.impl.visual;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.Vec3;
import rated.x.listener.bus.Listener;
import rated.x.listener.event.EventPlayerHurt;
import rated.x.listener.event.EventRenderHUD;
import rated.x.module.Module;
import rated.x.module.ModuleCategory;
import rated.x.module.ModuleManifest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xgraza
 * @since 1.0.0
 */
@ModuleManifest(name = "DamageIndicator",
        description = "Shows from which direction you were damaged from with arrows around the crosshair",
        category = ModuleCategory.VISUAL)
public final class ModuleDamageIndicator extends Module
{
    private final Map<Damage, Long> damageSourceDecayTimeMap = new ConcurrentHashMap<>();

    @Listener
    public void onRenderHUD(final EventRenderHUD event)
    {
        if (damageSourceDecayTimeMap.isEmpty())
        {
            return;
        }
        final GuiGraphics g = event.graphics();
        for (final Damage damage : damageSourceDecayTimeMap.keySet())
        {
            final long decayAtTime = damageSourceDecayTimeMap.get(damage);
            if (System.currentTimeMillis() > decayAtTime)
            {
                damageSourceDecayTimeMap.remove(damage);
            }

            final double diff = getDiff(damage.currentPosition(),
                    damage.damageSource().getSourcePosition()); // already checked this was non-null

            double x = (event.screenWidth() / 2.0) - Math.sin(diff) * 10;
            double y = (event.screenHeight() / 2.0) - 250 + Math.cos(diff) * 10;


            g.fill((int) x, (int) y, (int) (x + 5), (int) (y + 5), 0xFFAAAAAA);
        }
    }

    @Listener
    public void onPlayerHurt(final EventPlayerHurt event)
    {
        final DamageSource src = event.damageSource();
        final Vec3 position = src.getSourcePosition();
        if (position != null)
        {
            final Vec3 currentPosition = MC.player.position();
            damageSourceDecayTimeMap.put(new Damage(src, currentPosition), System.currentTimeMillis() + 1000L);
        }
    }

    private double getDiff(final Vec3 damagedAtVec, final Vec3 damagerVec)
    {
        final double deltaX = damagerVec.x - damagedAtVec.x;
        final double deltaZ = damagerVec.z - damagedAtVec.z;
        return Math.toRadians(Math.toDegrees(Math.atan2(deltaX, deltaZ)) - MC.player.getYRot());
    }

    private record Damage(DamageSource damageSource, Vec3 currentPosition)
    {

    }
}
