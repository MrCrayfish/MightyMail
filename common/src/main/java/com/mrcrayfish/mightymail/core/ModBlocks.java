package com.mrcrayfish.mightymail.core;

import com.mrcrayfish.framework.api.registry.RegistryContainer;
import com.mrcrayfish.framework.api.registry.RegistryEntry;
import com.mrcrayfish.mightymail.block.MailboxBlock;
import com.mrcrayfish.mightymail.block.PostBoxBlock;
import com.mrcrayfish.mightymail.util.Utils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

/**
 * Author: MrCrayfish
 */
@RegistryContainer
public class ModBlocks
{
    public static final RegistryEntry<MailboxBlock> MAIL_BOX_OAK = RegistryEntry.blockWithItem(Utils.resource("oak_mail_box"), () -> new MailboxBlock(BlockBehaviour.Properties.of().mapColor(Blocks.OAK_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.5F).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryEntry<MailboxBlock> MAIL_BOX_SPRUCE = RegistryEntry.blockWithItem(Utils.resource("spruce_mail_box"), () -> new MailboxBlock(BlockBehaviour.Properties.of().mapColor(Blocks.SPRUCE_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.5F).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryEntry<MailboxBlock> MAIL_BOX_BIRCH = RegistryEntry.blockWithItem(Utils.resource("birch_mail_box"), () -> new MailboxBlock(BlockBehaviour.Properties.of().mapColor(Blocks.BIRCH_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.5F).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryEntry<MailboxBlock> MAIL_BOX_JUNGLE = RegistryEntry.blockWithItem(Utils.resource("jungle_mail_box"), () -> new MailboxBlock(BlockBehaviour.Properties.of().mapColor(Blocks.JUNGLE_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.5F).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryEntry<MailboxBlock> MAIL_BOX_ACACIA = RegistryEntry.blockWithItem(Utils.resource("acacia_mail_box"), () -> new MailboxBlock(BlockBehaviour.Properties.of().mapColor(Blocks.ACACIA_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.5F).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryEntry<MailboxBlock> MAIL_BOX_DARK_OAK = RegistryEntry.blockWithItem(Utils.resource("dark_oak_mail_box"), () -> new MailboxBlock(BlockBehaviour.Properties.of().mapColor(Blocks.DARK_OAK_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.5F).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryEntry<MailboxBlock> MAIL_BOX_MANGROVE = RegistryEntry.blockWithItem(Utils.resource("mangrove_mail_box"), () -> new MailboxBlock(BlockBehaviour.Properties.of().mapColor(Blocks.MANGROVE_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.5F).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryEntry<MailboxBlock> MAIL_BOX_CHERRY = RegistryEntry.blockWithItem(Utils.resource("cherry_mail_box"), () -> new MailboxBlock(BlockBehaviour.Properties.of().mapColor(Blocks.CHERRY_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.5F).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryEntry<MailboxBlock> MAIL_BOX_CRIMSON = RegistryEntry.blockWithItem(Utils.resource("crimson_mail_box"), () -> new MailboxBlock(BlockBehaviour.Properties.of().mapColor(Blocks.CRIMSON_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.5F).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryEntry<MailboxBlock> MAIL_BOX_WARPED = RegistryEntry.blockWithItem(Utils.resource("warped_mail_box"), () -> new MailboxBlock(BlockBehaviour.Properties.of().mapColor(Blocks.WARPED_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.5F).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryEntry<PostBoxBlock> POST_BOX = RegistryEntry.blockWithItem(Utils.resource("post_box"), () -> new PostBoxBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).instrument(NoteBlockInstrument.IRON_XYLOPHONE).strength(3.5F).sound(SoundType.METAL).requiresCorrectToolForDrops()));

}
