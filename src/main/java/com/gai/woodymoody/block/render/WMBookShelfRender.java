package com.gai.woodymoody.block.render;

import com.gai.woodymoody.entity.WMBookshelfBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;

public class WMBookShelfRender implements BlockEntityRenderer<WMBookshelfBlockEntity> {

    @Override
    public void render(WMBookshelfBlockEntity blockEntity, float partialTicks, PoseStack stack, MultiBufferSource buffer, int combinedOverlay, int packedLight) {
        BlockRenderDispatcher dispatcher = Minecraft.getInstance().getBlockRenderer();
        //dispatcher.renderSingleBlock(Blocks.GLASS.defaultBlockState(), stack, buffer,);
    }
}
