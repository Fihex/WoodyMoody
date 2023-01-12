package com.gai.woodymoody.container;

import com.gai.woodymoody.block.WMBlocks;
import com.gai.woodymoody.block.WMBookShelf;
import com.gai.woodymoody.entity.WMBookshelfBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WMBookShelfContainer extends AbstractContainerMenu {

    public static final Logger LOGGER = LogManager.getLogger(WMBookShelfContainer.class.getName());

    private final ContainerLevelAccess containerLevelAccess;

    public WMBookShelfContainer(int id, Inventory playerInventory) {
        this(id, playerInventory, new ItemStackHandler(WMBookShelf.selfSize), BlockPos.ZERO);
    }

    public WMBookShelfContainer(int id, Inventory playerInventory, IItemHandler slots, BlockPos blockPos) {
        super(WMMenus.WM_BOOKSHELF.get(), id);
        this.containerLevelAccess = ContainerLevelAccess.create(playerInventory.player.level, blockPos);

        final int slotSizePlus2 = 18, startX = 48, startY = 86, hotBarY = 142, invY = 58;

        for (int row = 0; row < 1; row++) {
            for (int col = 0; col < WMBookShelf.selfSize; col++) {
                addSlot(new SlotItemHandler(slots, row * 9 + col, startX + col * slotSizePlus2,
                        invY + row * slotSizePlus2));
            }
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(playerInventory, 9 + row * 9 + col, startX + col * slotSizePlus2,
                        startY + row * slotSizePlus2 + 38));
            }
        }

        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(playerInventory, col, startX + col * slotSizePlus2, hotBarY + 40));
        }

    }

    @Override
    public void slotsChanged(Container container) {
        super.slotsChanged(container);
        LOGGER.debug("errraaa11111");
    }

    @Override
    public void addSlotListener(ContainerListener p_38894_) {
        super.addSlotListener(p_38894_);
        //LOGGER.debug("errraaa222222");
    }

    @Override
    public void resumeRemoteUpdates() {
        super.resumeRemoteUpdates();
        LOGGER.debug("errraaa333333");
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        var retStack = ItemStack.EMPTY;
        final Slot slot = getSlot(index);

        if (slot.hasItem()) {
            final ItemStack stack = slot.getItem();
            retStack = stack.copy();

            if (index < WMBookShelf.selfSize) {
                if (!moveItemStackTo(stack, WMBookShelf.selfSize, this.slots.size(), true))
                    return ItemStack.EMPTY;
            } else if (!moveItemStackTo(stack, 0, WMBookShelf.selfSize, false))
                return ItemStack.EMPTY;

            if (stack.isEmpty() || stack.getCount() == 0) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            /*if (stack.getCount() == retStack.getCount())
                return ItemStack.EMPTY;

            slot.onTake(player, stack);*/
        }

        return retStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.containerLevelAccess, player, WMBlocks.WM_BOOKSHELF.get());
    }

    public static MenuConstructor getServerContainer(WMBookshelfBlockEntity bookshelf, BlockPos blockPos) {
        return (id, playerInventory, player) -> new WMBookShelfContainer(id, playerInventory, bookshelf.inventory, blockPos);
    }
}
