/*
 * Copyright (c) xgraza 2025
 */

package rated.x.listener.event;

import net.minecraft.client.gui.GuiGraphics;

/**
 * @param graphics
 * @author xgraza
 * @since 1.0.0
 */
public record EventRenderHUD(GuiGraphics graphics, int screenWidth, int screenHeight)
{
}
