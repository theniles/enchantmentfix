package net.nile.enchant.fix.mixin;

import com.mojang.blaze3d.systems.RenderSystem;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

@Mixin(AnvilScreen.class)
public class AnvilScreenFix {
   @Inject(method = "drawForeground(Lnet/minecraft/client/util/math/MatrixStack;II)V", at = @At("HEAD"), cancellable = true)
   private void drawForeground(MatrixStack matrices, int mouseX, int mouseY, CallbackInfo cb) {

      AnvilScreenAccessor anvilScreen = (AnvilScreenAccessor)(Object)this;

      HandledScreenAccessor handledScreen = (HandledScreenAccessor)(Object)this;

      AbstractScreenAccessor abstractScreen = (AbstractScreenAccessor)(Object)this;

        RenderSystem.disableBlend();
        abstractScreen.getTextRenderer().draw(matrices, abstractScreen.getTitle(), (float)handledScreen.getTitleX(), (float)handledScreen.getTitleY(), 4210752);
        abstractScreen.getTextRenderer().draw(matrices, handledScreen.getPlayerInventory().getDisplayName(), (float)handledScreen.getPlayerInventoryTitleX(), (float)handledScreen.getPlayerInventoryTitleY(), 4210752);
        int i = ((AnvilScreenHandler)handledScreen.getHandler()).getLevelCost();
        if (i > 0) {
           int j = 8453920;
           Object text3;
           /*if (i >= 40 && !this.client.player.abilities.creativeMode) {
              text3 = field_26559;
              j = 16736352;
           } else*/ if (!(handledScreen.getHandler().getSlot(2).hasStack())) {
              text3 = null;
           } else {
              text3 = new TranslatableText("container.repair.cost", new Object[]{i});
              if (!handledScreen.getHandler().getSlot(2).canTakeItems(handledScreen.getPlayerInventory().player)) {
                 j = 16736352;
              }
           }
  
           if (text3 != null) {
              int k = handledScreen.getBackgroundWidth() - 8 - abstractScreen.getTextRenderer().getWidth((StringVisitable)text3) - 2;
              net.minecraft.client.gui.DrawableHelper.fill(matrices, k - 2, 67, handledScreen.getBackgroundWidth() - 8, 79, 1325400064);
              abstractScreen.getTextRenderer().drawWithShadow(matrices, (Text)text3, (float)k, 69.0F, j);
           }
        }
  
         cb.cancel();;

     }
}
