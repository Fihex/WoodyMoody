package com.gai.woodymoody.entity;

import com.gai.woodymoody.WoodyMoody;
import com.gai.woodymoody.block.WMBookShelf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;

public class WMBookshelfBlockEntity extends InventoryBlockEntity{

    public static final Component TITLE = Component.translatable("container." + WoodyMoody.MOD_ID + ".wm_bookshelf");

    public WMBookshelfBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(WMBlockEntities.WM_BLOCK_BOOKSHELF.get(),blockPos, blockState, WMBookShelf.selfSize);
    }



}
