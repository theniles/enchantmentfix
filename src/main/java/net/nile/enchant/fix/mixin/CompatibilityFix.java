package net.nile.enchant.fix.mixin;

import java.util.Collection;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;

@Mixin(EnchantmentHelper.class)
public class CompatibilityFix {
    // @Inject(method = "isCompatible()Z", cancellable = true, at = @At("HEAD"))
    // private static void injectMethod(Collection<Enchantment> existing, Enchantment candidate, CallbackInfoReturnable<Boolean> info) {
    //     // EnchantFix.logger.info("Mixin called!");
    //     // info.setReturnValue(true);
    //     // //EnchantmentHelper
    //     //AnvilScreenHandler
    // }
}
