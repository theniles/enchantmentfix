package net.nile.enchant.fix.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;

@Mixin(HandledScreen.class)
public interface HandledScreenAccessor {
    //ImpalingEnchantment
    //TridentItem
    @Accessor("handler")
    public ScreenHandler getHandler();

    @Accessor("backgroundWidth")
    public int getBackgroundWidth();

    @Accessor("backgroundHeight")
    public int getBackgroundHeight();

    @Accessor("titleX")
    public int getTitleX();

    @Accessor("titleY")
    public int getTitleY();

    @Accessor("playerInventoryTitleX")
    public int getPlayerInventoryTitleX();

    @Accessor("playerInventoryTitleY")
    public int getPlayerInventoryTitleY();

    @Accessor("playerInventoryTitle")
    public Text getPlayerInventoryTitle();
}
