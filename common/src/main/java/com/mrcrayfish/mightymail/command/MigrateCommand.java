package com.mrcrayfish.mightymail.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mrcrayfish.framework.platform.Services;
import com.mrcrayfish.mightymail.block.MailboxBlock;
import com.mrcrayfish.mightymail.blockentity.MailboxBlockEntity;
import com.mrcrayfish.mightymail.core.ModBlocks;
import com.mrcrayfish.mightymail.mail.DeliveryService;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: MrCrayfish
 */
public class MigrateCommand
{
    private static final WeakHashMap<UUID, Long> PENDING = new WeakHashMap<>();

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
    {
        dispatcher.register(Commands.literal("mighty_mail:migrate")
            .requires(source -> source.hasPermission(2) && source.isPlayer())
            .executes(context -> {
                ServerPlayer player = context.getSource().getPlayer();
                if(player != null) {
                    PENDING.put(player.getUUID(), Util.getMillis());
                    MutableComponent link = Component.literal("CONFIRM");
                    link.setStyle(link.getStyle().withBold(true).withColor(ChatFormatting.AQUA).withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/mighty_mail:confirm")));
                    context.getSource().sendSuccess(() -> Component.literal("This is an irreversible action. Click ").append(link).append(" to start action."), false);
                    return 1;
                }
                return 0;
            }));
        dispatcher.register(Commands.literal("mighty_mail:confirm")
            .requires(source -> source.hasPermission(2) && source.isPlayer())
            .executes(context -> {
                ServerPlayer player = context.getSource().getPlayer();
                if(player != null && PENDING.containsKey(player.getUUID())) {
                    if(Util.getMillis() - PENDING.get(player.getUUID()) >= 10000) {
                        PENDING.remove(player.getUUID());
                        context.getSource().sendFailure(Component.literal("Action expired. You must confirm within 10 seconds."));
                        return 0;
                    }
                    PENDING.remove(player.getUUID());
                    migrateMailboxes(context.getSource());
                    return 1;
                }
                context.getSource().sendFailure(Component.literal("Invalid confirmation. You must run /mighty_mail:migrate first"));
                return 0;
            }));
    }

    private static int migrateMailboxes(CommandSourceStack source)
    {
        MinecraftServer server = source.getServer();

        com.mrcrayfish.furniture.refurbished.mail.DeliveryService refurbishedService =
            com.mrcrayfish.furniture.refurbished.mail.DeliveryService.get(server).orElse(null);

        if(refurbishedService == null)
        {
            source.sendFailure(Component.literal("Failed to get mailbox registry from the furniture mod. This should not happen..."));
            return 0;
        }

        DeliveryService service = DeliveryService.get(server).orElse(null);
        if(service == null)
        {
            source.sendFailure(Component.literal("Failed to get mailbox registry from mighty mail. This should not happen..."));
            return 0;
        }

        AtomicInteger counter = new AtomicInteger(0);
        service.getMailboxes().forEach((uuid, mailbox) ->
        {
            // Get the level of the mailbox
            ServerLevel level = server.getLevel(mailbox.levelKey());
            if(level == null)
                return;

            // Get the block entity
            BlockEntity entity = level.getBlockEntity(mailbox.pos());
            if(!(entity instanceof MailboxBlockEntity))
                return;

            BlockState state = level.getBlockState(mailbox.pos());
            if(!(state.getBlock() instanceof MailboxBlock))
                return;

            // Get the properties of the mighty mail mailbox
            Direction direction = state.getValue(MailboxBlock.DIRECTION);
            boolean enabled = state.getValue(MailboxBlock.ENABLED);

            // Get the equivalent block to replace
            Block newBlock;
            if(state.getBlock() == ModBlocks.MAIL_BOX_OAK.get())
            {
                newBlock = com.mrcrayfish.furniture.refurbished.core.ModBlocks.MAIL_BOX_OAK.get();
            }
            else if(state.getBlock() == ModBlocks.MAIL_BOX_SPRUCE.get())
            {
                newBlock = com.mrcrayfish.furniture.refurbished.core.ModBlocks.MAIL_BOX_SPRUCE.get();
            }
            else if(state.getBlock() == ModBlocks.MAIL_BOX_BIRCH.get())
            {
                newBlock = com.mrcrayfish.furniture.refurbished.core.ModBlocks.MAIL_BOX_BIRCH.get();
            }
            else if(state.getBlock() == ModBlocks.MAIL_BOX_JUNGLE.get())
            {
                newBlock = com.mrcrayfish.furniture.refurbished.core.ModBlocks.MAIL_BOX_JUNGLE.get();
            }
            else if(state.getBlock() == ModBlocks.MAIL_BOX_ACACIA.get())
            {
                newBlock = com.mrcrayfish.furniture.refurbished.core.ModBlocks.MAIL_BOX_ACACIA.get();
            }
            else if(state.getBlock() == ModBlocks.MAIL_BOX_DARK_OAK.get())
            {
                newBlock = com.mrcrayfish.furniture.refurbished.core.ModBlocks.MAIL_BOX_DARK_OAK.get();
            }
            else if(state.getBlock() == ModBlocks.MAIL_BOX_MANGROVE.get())
            {
                newBlock = com.mrcrayfish.furniture.refurbished.core.ModBlocks.MAIL_BOX_MANGROVE.get();
            }
            else if(state.getBlock() == ModBlocks.MAIL_BOX_CRIMSON.get())
            {
                newBlock = com.mrcrayfish.furniture.refurbished.core.ModBlocks.MAIL_BOX_CRIMSON.get();
            }
            else if(state.getBlock() == ModBlocks.MAIL_BOX_WARPED.get())
            {
                newBlock = com.mrcrayfish.furniture.refurbished.core.ModBlocks.MAIL_BOX_WARPED.get();
            }
            else
            {
                return;
            }

            // Set the new blockstate
            BlockState state1 = newBlock.defaultBlockState()
                .setValue(BlockStateProperties.HORIZONTAL_FACING, direction)
                .setValue(BlockStateProperties.ENABLED, enabled);
            level.setBlock(mailbox.pos(), state1, Block.UPDATE_ALL);

            // Set the new block entity
            com.mrcrayfish.furniture.refurbished.blockentity.MailboxBlockEntity newBlockEntity = new com.mrcrayfish.furniture.refurbished.blockentity.MailboxBlockEntity(mailbox.pos(), state1);
            level.setBlockEntity(newBlockEntity);
            newBlockEntity.regenerateId();

            // Update the details of the new mailbox to match the old name and owner
            com.mrcrayfish.furniture.refurbished.mail.Mailbox newMailbox = newBlockEntity.getMailbox();
            newMailbox.customName().setValue(mailbox.customName().getValue());
            newMailbox.setOwner(mailbox.owner().getValue());

            // Count
            counter.incrementAndGet();
        });

        source.sendSuccess(() ->Component.literal("Successfully migrated %s mailboxes".formatted(counter.get())).withStyle(ChatFormatting.GREEN), false);

        return 1;
    }
}
