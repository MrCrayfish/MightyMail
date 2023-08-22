package com.mrcrayfish.mightymail.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: MrCrayfish
 */
public class ScreenHelper
{
    /**
     * Splits the given string into separate lines, which is determined by the maximum width. Unlike
     * vanilla utilities, this method returns a list of components. This can be used to add additional
     * lines using {@link net.minecraft.world.item.Item#appendHoverText(ItemStack, Level, List, TooltipFlag)}
     *
     * @param text     the text to split
     * @param maxWidth the maximum width of a line
     * @return a list of components
     */
    public static List<MutableComponent> splitText(String text, int maxWidth)
    {
        return Minecraft.getInstance().font.getSplitter().splitLines(text, maxWidth, Style.EMPTY).stream().map(t -> Component.literal(t.getString())).collect(Collectors.toList());
    }
}
