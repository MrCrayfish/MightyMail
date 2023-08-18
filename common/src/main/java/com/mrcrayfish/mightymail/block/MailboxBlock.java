package com.mrcrayfish.mightymail.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.mightymail.blockentity.MailboxBlockEntity;
import com.mrcrayfish.mightymail.client.ScreenHooks;
import com.mrcrayfish.mightymail.mail.DeliveryService;
import com.mrcrayfish.mightymail.mail.Mailbox;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Author: MrCrayfish
 */
public class MailboxBlock extends RotatedBlock implements EntityBlock
{
    public static final BooleanProperty ENABLED = BlockStateProperties.ENABLED;

    private final Map<BlockState, VoxelShape> shapes;

    public MailboxBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(DIRECTION, Direction.NORTH).setValue(ENABLED, false));
        this.shapes = this.generateShapes();
    }

    protected Map<BlockState, VoxelShape> generateShapes()
    {
        ImmutableList<BlockState> states = this.getStateDefinition().getPossibleStates();
        VoxelShape standShape = Block.box(6.5, 0, 6.5, 9.5, 11, 9.5);
        VoxelShape boxShape = Block.box(3, 11, 3, 13, 20, 13);
        VoxelShape joinedShape = Shapes.joinUnoptimized(standShape, boxShape, BooleanOp.OR).optimize();
        return ImmutableMap.copyOf(states.stream().collect(Collectors.toMap(state -> state, o -> joinedShape)));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context)
    {
        return this.shapes.get(state);
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos)
    {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state)
    {
        return true;
    }

    @Override
    public RenderShape getRenderShape(BlockState state)
    {
        return RenderShape.MODEL;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack)
    {
        if(entity instanceof Player player)
        {
            if(!level.isClientSide())
            {
                if(level.getBlockEntity(pos) instanceof MailboxBlockEntity mailbox)
                {
                    mailbox.getMailbox().owner().setValue(player.getUUID());
                    DeliveryService.get(((ServerLevel) level).getServer()).ifPresent(service -> {
                        service.markMailboxAsPendingName(player, level, pos);
                    });
                }
            }
            else
            {
                ScreenHooks.openMailboxNameScreen(pos);
            }
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if(!level.isClientSide() && !state.is(newState.getBlock()))
        {
            if(level.getBlockEntity(pos) instanceof MailboxBlockEntity mailbox)
            {
                Optional.ofNullable(mailbox.getMailbox()).ifPresent(Mailbox::remove);
                Containers.dropContents(level, pos, mailbox);
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result)
    {
        if(!level.isClientSide() && level.getBlockEntity(pos) instanceof MailboxBlockEntity mailbox)
        {
            // Remove the little flag once the player open the mailbox
            if(state.getValue(ENABLED))
            {
                level.setBlock(pos, state.setValue(ENABLED, false), Block.UPDATE_ALL);
            }
            player.openMenu(mailbox);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(ENABLED);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new MailboxBlockEntity(pos, state);
    }
}
