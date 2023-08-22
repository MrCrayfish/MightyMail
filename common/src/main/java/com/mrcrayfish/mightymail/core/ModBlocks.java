package com.mrcrayfish.mightymail.core;

import com.mrcrayfish.framework.api.registry.RegistryContainer;
import com.mrcrayfish.framework.api.registry.RegistryEntry;
import com.mrcrayfish.mightymail.block.MailboxBlock;
import com.mrcrayfish.mightymail.block.PostBoxBlock;
import com.mrcrayfish.mightymail.util.Utils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

/**
 * Author: MrCrayfish
 */
@RegistryContainer
public class ModBlocks
{
    public static final RegistryEntry<MailboxBlock> MAIL_BOX_OAK = RegistryEntry.blockWithItem(Utils.resource("oak_mail_box"), () -> new MailboxBlock(BlockBehaviour.Properties.of(Material.WOOD).color(Blocks.OAK_PLANKS.defaultMaterialColor()).strength(3.5F).sound(SoundType.WOOD)));
    public static final RegistryEntry<MailboxBlock> MAIL_BOX_SPRUCE = RegistryEntry.blockWithItem(Utils.resource("spruce_mail_box"), () -> new MailboxBlock(BlockBehaviour.Properties.of(Material.WOOD).color(Blocks.SPRUCE_PLANKS.defaultMaterialColor()).strength(3.5F).sound(SoundType.WOOD)));
    public static final RegistryEntry<MailboxBlock> MAIL_BOX_BIRCH = RegistryEntry.blockWithItem(Utils.resource("birch_mail_box"), () -> new MailboxBlock(BlockBehaviour.Properties.of(Material.WOOD).color(Blocks.BIRCH_PLANKS.defaultMaterialColor()).strength(3.5F).sound(SoundType.WOOD)));
    public static final RegistryEntry<MailboxBlock> MAIL_BOX_JUNGLE = RegistryEntry.blockWithItem(Utils.resource("jungle_mail_box"), () -> new MailboxBlock(BlockBehaviour.Properties.of(Material.WOOD).color(Blocks.JUNGLE_PLANKS.defaultMaterialColor()).strength(3.5F).sound(SoundType.WOOD)));
    public static final RegistryEntry<MailboxBlock> MAIL_BOX_ACACIA = RegistryEntry.blockWithItem(Utils.resource("acacia_mail_box"), () -> new MailboxBlock(BlockBehaviour.Properties.of(Material.WOOD).color(Blocks.ACACIA_PLANKS.defaultMaterialColor()).strength(3.5F).sound(SoundType.WOOD)));
    public static final RegistryEntry<MailboxBlock> MAIL_BOX_DARK_OAK = RegistryEntry.blockWithItem(Utils.resource("dark_oak_mail_box"), () -> new MailboxBlock(BlockBehaviour.Properties.of(Material.WOOD).color(Blocks.DARK_OAK_PLANKS.defaultMaterialColor()).strength(3.5F).sound(SoundType.WOOD)));
    public static final RegistryEntry<MailboxBlock> MAIL_BOX_MANGROVE = RegistryEntry.blockWithItem(Utils.resource("mangrove_mail_box"), () -> new MailboxBlock(BlockBehaviour.Properties.of(Material.WOOD).color(Blocks.MANGROVE_PLANKS.defaultMaterialColor()).strength(3.5F).sound(SoundType.WOOD)));
    public static final RegistryEntry<MailboxBlock> MAIL_BOX_CHERRY = RegistryEntry.blockWithItem(Utils.resource("cherry_mail_box"), () -> new MailboxBlock(BlockBehaviour.Properties.of(Material.WOOD).color(Blocks.CHERRY_PLANKS.defaultMaterialColor()).strength(3.5F).sound(SoundType.WOOD)));
    public static final RegistryEntry<MailboxBlock> MAIL_BOX_CRIMSON = RegistryEntry.blockWithItem(Utils.resource("crimson_mail_box"), () -> new MailboxBlock(BlockBehaviour.Properties.of(Material.WOOD).color(Blocks.CRIMSON_PLANKS.defaultMaterialColor()).strength(3.5F).sound(SoundType.WOOD)));
    public static final RegistryEntry<MailboxBlock> MAIL_BOX_WARPED = RegistryEntry.blockWithItem(Utils.resource("warped_mail_box"), () -> new MailboxBlock(BlockBehaviour.Properties.of(Material.WOOD).color(Blocks.WARPED_PLANKS.defaultMaterialColor()).strength(3.5F).sound(SoundType.WOOD)));
    public static final RegistryEntry<PostBoxBlock> POST_BOX = RegistryEntry.blockWithItem(Utils.resource("post_box"), () -> new PostBoxBlock(BlockBehaviour.Properties.of(Material.METAL).color(MaterialColor.COLOR_BLUE).strength(3.5F).sound(SoundType.METAL).requiresCorrectToolForDrops()));

}
