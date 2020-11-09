package net.nile.enchant.fix.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ForgingScreenHandler;

@Mixin(ForgingScreenHandler.class)
public interface ForgingAccessor {
    @Accessor("input")
    public Inventory getInput();

    @Accessor("output")
    public CraftingResultInventory getOutput();

    @Accessor("player")
    public PlayerEntity getPlayer();
}