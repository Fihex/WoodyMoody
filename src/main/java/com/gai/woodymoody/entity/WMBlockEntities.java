package com.gai.woodymoody.entity;

import com.gai.woodymoody.WoodyMoody;
import com.gai.woodymoody.block.WMBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WMBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, WoodyMoody.MOD_ID);

    public static final RegistryObject<BlockEntityType<WMBookshelfBlockEntity>> WM_BLOCK_BOOKSHELF =
            BLOCK_ENTITIES.register("wm_block_bookshelf", () ->
                    BlockEntityType.Builder.of(WMBookshelfBlockEntity::new,
                            WMBlocks.WM_BOOKSHELF.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
