package rated.x.module.impl.hud;

import com.google.common.collect.Lists;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import rated.x.gui.GUIModule;
import rated.x.module.ModuleCategory;
import rated.x.module.ModuleManifest;

import java.util.Collection;
import java.util.List;

/**
 * @author xgraza
 * @since 1.0.0
 */
@ModuleManifest(name = "PotionDisplay",
        description = "Displays potion effects with levels and duration",
        category = ModuleCategory.HUD)
public final class ModulePotionDisplay extends GUIModule
{
    private static final List<MobEffectInstance> PREVIEW_POTION_EFFECTS = Lists.newArrayList(
            new MobEffectInstance(MobEffects.BAD_OMEN, 2000)
    );

    @Override
    public void render(final GuiGraphics graphics,
                       final int screenWidth,
                       final int screenHeight,
                       final boolean preview)
    {
        super.render(graphics, screenWidth, screenHeight, preview);

        var effects = getEffects(preview);
        if (effects.isEmpty())
        {
            return;
        }


    }

    private Collection<MobEffectInstance> getEffects(final boolean preview)
    {
        var items = MC.player.getActiveEffects();
        if (items.isEmpty() && preview)
        {
            return PREVIEW_POTION_EFFECTS;
        }
        return items;
    }
}
