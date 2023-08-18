package com.mrcrayfish.mightymail.platform.services;

import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.util.FormattedCharSequence;

import java.util.List;

/**
 * Author: MrCrayfish
 */
public interface IClientHelper
{
    void setTooltipCache(Tooltip tooltip, List<FormattedCharSequence> lines);
}
