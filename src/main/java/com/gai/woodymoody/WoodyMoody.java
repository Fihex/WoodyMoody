package com.gai.woodymoody;

import com.gai.woodymoody.block.WMBlocks;
import com.gai.woodymoody.container.WMMenus;
import com.gai.woodymoody.entity.WMBlockEntities;
import com.gai.woodymoody.item.WMItems;
import com.gai.woodymoody.screen.WMBookShelfScreen;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(WoodyMoody.MOD_ID)
public class WoodyMoody
{
    public static final String MOD_ID = "woodymoody";
    private static final Logger LOGGER = LogUtils.getLogger();

    public WoodyMoody()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        WMItems.register(modEventBus);
        WMBlocks.register(modEventBus);

        WMBlockEntities.register(modEventBus);
        WMMenus.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(WMMenus.WM_BOOKSHELF.get(), WMBookShelfScreen::new);
        }
    }
}
