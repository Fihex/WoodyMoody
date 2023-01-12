package com.gai.woodymoody.container;

import com.gai.woodymoody.WoodyMoody;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class WMMenus {
    public static final DeferredRegister<MenuType<?>> MENUS  = DeferredRegister.create(ForgeRegistries.MENU_TYPES, WoodyMoody.MOD_ID);

    public static final RegistryObject<MenuType<WMBookShelfContainer>> WM_BOOKSHELF =
            registerMenuType(new MenuType<>(WMBookShelfContainer::new), "wm_shelfbook_menu");

            //MENUS.register("wm_shelfbook", () -> new MenuType<>(WMBookShelfContainer::new));

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(MenuType<T> menuType, String name) {
        return MENUS.register(name, () -> menuType);
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
