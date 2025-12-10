/*
 * Copyright (c) xgraza 2025
 */

package rated.x.module.impl.hud;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityEquipment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import rated.x.gui.GUIModule;
import rated.x.mixin.duck.ILivingEntity;
import rated.x.module.ModuleCategory;
import rated.x.module.ModuleManifest;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xgraza
 * @since 1.0.0
 */
@ModuleManifest(name = "Armor",
        description = "Displays armor and their durability values",
        category = ModuleCategory.HUD)
public final class ModuleArmor extends GUIModule
{
    @Override
    public void render(final GuiGraphics graphics,
                       final int screenWidth,
                       final int screenHeight,
                       final boolean preview)
    {
        super.render(graphics, screenWidth, screenHeight, preview);

        setHeight((18 * 6) + 4);
        setWidth(60);

        final List<ItemStack> equipmentList = getEquipment();
        if (equipmentList.isEmpty())
        {
            return;
        }

        for (int i = equipmentList.size() - 1; i >= 0; --i)
        {
            final ItemStack itemStack = equipmentList.get(i);
            final int posX = (int) (x + 2);
            final int posY = (int) (y + height - 2 - (18 * (i + 1)));
            graphics.renderItem(itemStack, posX, posY);
            graphics.renderItemDecorations(MC.font, itemStack, posX, posY);

            if (itemStack.getMaxDamage() != 0)
            {
                final double percent = 1.0 - (itemStack.getDamageValue() / (double) itemStack.getMaxDamage());
                graphics.drawString(MC.font,
                        String.format("%.1f", percent * 100.0) + "%",
                        posX + 18, posY + 4,
                        new Color((int) (255 * (1.0 - percent)), (int) (255 * percent), 0).getRGB());
            }
        }
    }

    private List<ItemStack> getEquipment()
    {
        final List<ItemStack> equipmentList = new LinkedList<>();
        final EntityEquipment equipment = ((ILivingEntity) MC.player).hook_ratedX$equipment();
        equipmentList.add(equipment.get(EquipmentSlot.FEET));
        equipmentList.add(equipment.get(EquipmentSlot.LEGS));
        equipmentList.add(equipment.get(EquipmentSlot.CHEST));
        equipmentList.add(equipment.get(EquipmentSlot.HEAD));
        equipmentList.add(MC.player.getInventory().getSelectedItem());
        equipmentList.add(MC.player.getItemInHand(InteractionHand.OFF_HAND));
        equipmentList.removeIf(ItemStack::isEmpty);
        return equipmentList;
    }
}
