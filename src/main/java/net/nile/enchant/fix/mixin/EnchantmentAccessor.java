package net.nile.enchant.fix.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;

@Mixin(Enchantment.class)
public interface EnchantmentAccessor{
// @Accessor("type")
// public void setType(EnchantmentTarget t);
}