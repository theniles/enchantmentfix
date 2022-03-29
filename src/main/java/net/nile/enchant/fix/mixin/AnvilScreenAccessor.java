package net.nile.enchant.fix.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.ForgingScreenHandler;

@Mixin(AnvilScreen.class)
public interface AnvilScreenAccessor<T extends ForgingScreenHandler> {
    @Accessor("player")
    public PlayerEntity getPlayer();
}
