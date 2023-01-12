package com.gai.woodymoody.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class WMCreativeModeTab {
    public static final CreativeModeTab WM_TAB = new CreativeModeTab("woodymoodytab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.OAK_LOG);
        }
    };
}
