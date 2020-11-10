package net.nile.enchant.fix.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.ImpalingEnchantment;
import net.minecraft.entity.EquipmentSlot;

@Mixin(ImpalingEnchantment.class)
public class ImpalingFix {
    @Inject(method="<init>(Lnet/minecraft/enchantment/Enchantment$Rarity;[Lnet/minecraft/entity/EquipmentSlot;)V", at = @At("TAIL"))
    private void nileConstructor(Enchantment.Rarity rarity, EquipmentSlot[] slot ,CallbackInfo cb)
    {
        ((EnchantmentAccessor)((Enchantment)(Object)this)).setType(EnchantmentTarget.WEAPON);
    }
}
