package com.gai.woodymoody.block;

import com.gai.woodymoody.WoodyMoody;
import com.gai.woodymoody.item.WMCreativeModeTab;
import com.gai.woodymoody.item.WMItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class WMBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, WoodyMoody.MOD_ID);

    public static final RegistryObject<Block> TEST_BLOCK = registerBlock("test_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOD)
            .strength(0.25f)
            .sound(SoundType.WOOD)
            .requiresCorrectToolForDrops()), WMCreativeModeTab.WM_TAB);

    public static final RegistryObject<Block> WM_BOOKSHELF = registerBlock("wm_bookshelf",
            () -> new WMBookShelf(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(0.25f)
                    .sound(SoundType.WOOD)
                    .requiresCorrectToolForDrops()), WMCreativeModeTab.WM_TAB);

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registryBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registryBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return WMItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
