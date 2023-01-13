package com.gai.woodymoody.container;

import com.gai.woodymoody.block.WMBookShelf;
import com.gai.woodymoody.entity.WMBookshelfBlockEntity;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WMBookShelfContainerData extends SimpleContainerData {
    public static final Logger LOGGER = LogManager.getLogger(WMBookShelf.class.getName());

    private final WMBookshelfBlockEntity blockEntity;

    public WMBookShelfContainerData(WMBookshelfBlockEntity blockEntity, int count) {
        super(count);
        this.blockEntity = blockEntity;
    }

    @Override
    public int get(int key) {
        int x = 0;
        for (int i = 0; i < WMBookShelf.selfSize; i++) {
            if (this.blockEntity.getItemInSlot(i).getCount() > 0) {
                x++;
            }
        }
        return x;
        /*return switch(key) {
            case 0 -> this.blockEntity.getItemInSlot(0).getCount();
            default -> throw  new UnsupportedOperationException("No value for key: " + key +" in "+this.blockEntity);
        };*/
    }

    @Override
    public void set(int key, int value) {
        for (int i = 0; i < WMBookShelf.selfSize; i++) {
            ItemStack stack = this.blockEntity.getItemInSlot(i);
            if (value > 0 && value < stack.getMaxStackSize()) {
                stack.setCount(value);
            } else if (value <= 0) {
                stack = ItemStack.EMPTY;
            } else if (value > stack.getMaxStackSize()) {
                stack.setCount(stack.getMaxStackSize());
            }
            this.blockEntity.inventory.setStackInSlot(i, stack);
        }
    }
}
