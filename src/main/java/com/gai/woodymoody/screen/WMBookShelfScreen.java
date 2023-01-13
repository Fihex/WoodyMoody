package com.gai.woodymoody.screen;

import com.gai.woodymoody.WoodyMoody;
import com.gai.woodymoody.block.WMBlocks;
import com.gai.woodymoody.block.WMBookShelf;
import com.gai.woodymoody.container.WMBookShelfContainer;
import com.gai.woodymoody.entity.WMBookshelfBlockEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WMBookShelfScreen extends AbstractContainerScreen<WMBookShelfContainer> {
    public static final Logger LOGGER = LogManager.getLogger(WMBookShelf.class.getName());

    private static final ResourceLocation TEXTURE = new ResourceLocation(WoodyMoody.MOD_ID, "textures/gui/wm_bookshelf_gui.png");

    private static final int centerNum = 40;

    public WMBookShelfScreen(WMBookShelfContainer container, Inventory inventory, Component title) {
        super(container, inventory, title);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 256;
        this.imageHeight = 256;
    }

    @Override
    protected void renderBg(PoseStack stack, float mouseX, int mouseY, int partialTicks) {
        renderBackground(stack);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor( 1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        blit(stack, this.leftPos + centerNum, this.topPos + centerNum, 0, 0, this.imageWidth, this.imageHeight);

    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        this.font.draw(stack, title, this.leftPos + 7 + centerNum, this.topPos + 7 + centerNum, 0x404040);
        this.font.draw(stack, playerInventoryTitle, this.leftPos + 7 + centerNum, this.topPos + 73 + centerNum, 0x404040);
        /*drawString(stack, font, title, this.leftPos + 7 + centerNum, this.topPos + 7 + centerNum, 0x404040);
        drawString(stack, font, playerInventoryTitle, this.leftPos + 7 + centerNum, this.topPos + 73 + centerNum, 0x404040);*/

        int count = this.menu.data.get(0);
        LOGGER.debug("COUNT: " + count);
    }

    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {

    }
}
