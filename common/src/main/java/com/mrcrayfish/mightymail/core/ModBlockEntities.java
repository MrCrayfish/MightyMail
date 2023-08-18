package com.mrcrayfish.mightymail.core;

import com.mrcrayfish.framework.api.registry.RegistryContainer;
import com.mrcrayfish.framework.api.registry.RegistryEntry;
import com.mrcrayfish.mightymail.blockentity.MailboxBlockEntity;
import com.mrcrayfish.mightymail.blockentity.PostBoxBlockEntity;
import com.mrcrayfish.mightymail.util.Utils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * Author: MrCrayfish
 */
@RegistryContainer
public class ModBlockEntities
{
    public static final RegistryEntry<BlockEntityType<MailboxBlockEntity>> MAIL_BOX = RegistryEntry.blockEntity(Utils.resource("mail_box"), MailboxBlockEntity::new, () -> new Block[]{
            ModBlocks.MAIL_BOX_OAK.get(),
            ModBlocks.MAIL_BOX_SPRUCE.get(),
            ModBlocks.MAIL_BOX_BIRCH.get(),
            ModBlocks.MAIL_BOX_JUNGLE.get(),
            ModBlocks.MAIL_BOX_ACACIA.get(),
            ModBlocks.MAIL_BOX_DARK_OAK.get(),
            ModBlocks.MAIL_BOX_MANGROVE.get(),
            ModBlocks.MAIL_BOX_CHERRY.get(),
            ModBlocks.MAIL_BOX_CRIMSON.get(),
            ModBlocks.MAIL_BOX_WARPED.get()
    });

    public static final RegistryEntry<BlockEntityType<PostBoxBlockEntity>> POST_BOX = RegistryEntry.blockEntity(Utils.resource("post_box"), PostBoxBlockEntity::new, () -> new Block[]{
            ModBlocks.POST_BOX.get()
    });
}
