package com.mrcrayfish.mightymail.platform;

import com.google.common.collect.ImmutableList;
import com.mrcrayfish.mightymail.platform.services.IClientHelper;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.util.FormattedCharSequence;

import java.util.List;

public class ForgeClientHelper implements IClientHelper
{
    @Override
    public void setTooltipCache(Tooltip tooltip, List<FormattedCharSequence> lines)
    {
        tooltip.cachedTooltip = ImmutableList.copyOf(lines);
    }
}