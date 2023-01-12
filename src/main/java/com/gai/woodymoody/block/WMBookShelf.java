package com.gai.woodymoody.block;

import com.gai.woodymoody.container.WMBookShelfContainer;
import com.gai.woodymoody.container.WMMenus;
import com.gai.woodymoody.entity.WMBlockEntities;
import com.gai.woodymoody.entity.WMBookshelfBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class WMBookShelf extends HorizontalDirectionalBlock implements EntityBlock {

    public static final Logger LOGGER = LogManager.getLogger(WMBookShelf.class.getName());
    public static final int selfSize = 3;

    public WMBookShelf(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
                                                                  BlockEntityType<T> beType) {
        return level.isClientSide ? null
                : (level0, pos, state0, blockEntity) -> ((WMBookshelfBlockEntity) blockEntity).tick();
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos pos, BlockState newBlockState, boolean b) {
        /*if (!blockState.is(newBlockState.getBlock())) {
            // (BlockState p_51538_, Level p_51539_, BlockPos p_51540_, BlockState p_51541_, boolean p_51542_)
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof Container) {
                Containers.dropContents(level, pos, (Container)blockentity);
                level.updateNeighbourForOutputSignal(pos, this);
            }
            if (level.getBlockEntity(pos) instanceof ChestBlockEntity containerEntity) {
                Containers.dropContents(level, pos, containerEntity);
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), ItemRegistry.LOCK.get().getDefaultInstance());
                level.updateNeighbourForOutputSignal(pos, this);
            }

        }*/
        if (level.getBlockEntity(pos) instanceof WMBookshelfBlockEntity chest) {

            super.onRemove(blockState, level, pos, newBlockState, b);

            for (int i = 0; i < chest.size; i++) {
                ItemEntity entity = new ItemEntity(level, pos.getX() + .5f, pos.getY() + .3f, pos.getZ() + .5f, chest.getInventory().getStackInSlot(i));
                Vec3 motion = entity.getDeltaMovement();
                entity.push(-motion.x, -motion.y, -motion.z);
                level.addFreshEntity(entity);

            }
        }
       /* BlockEntity blockentity = level.getBlockEntity(pos);

        Containers.dropContents(level, pos, WMBookShelfContainer.);
        //Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), ((Container) blockentity).getItem(2));*/

    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new WMBookshelfBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
                                 BlockHitResult result) {
        if (!level.isClientSide && level.getBlockEntity(pos) instanceof final WMBookshelfBlockEntity chest) {
            final MenuProvider container = new SimpleMenuProvider(WMBookShelfContainer.getServerContainer(chest, pos),
                    WMBookshelfBlockEntity.TITLE);
            NetworkHooks.openScreen((ServerPlayer) player, container, pos);
            //level.playSound(player, pos, SoundEvents.BARREL_OPEN, SoundSource.BLOCKS, 3f, 3f);

        }

        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }
}
