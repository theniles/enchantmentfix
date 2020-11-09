package net.nile.enchant.fix.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.enchantment.DamageEnchantment;
//MendingEnchantment
//PlayerEntity
//ItemStack
//BowItem
//ToolItem
//ItemStack
//ExperienceOrbEntity
import net.minecraft.enchantment.Enchantment;

@Mixin(DamageEnchantment.class)
public class DamageFix {
    @Inject(method = "canAccept(Lnet/minecraft/enchantment/Enchantment;)Z", at = @At("HEAD"), cancellable = true)
    private void nileCanAccept(Enchantment ench, CallbackInfoReturnable<Boolean> cb)
    {
        cb.setReturnValue(((Enchantment)(Object)this) != ench);
    }
}
