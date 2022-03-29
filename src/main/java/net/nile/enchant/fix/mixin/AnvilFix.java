package net.nile.enchant.fix.mixin;

import java.util.Iterator;
import java.util.Map;

import net.minecraft.item.Items;
import net.minecraft.screen.AnvilScreenHandler;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import org.apache.commons.lang3.StringUtils;

@Mixin(AnvilScreenHandler.class)
public class AnvilFix {
//ForgingScreenHandler
//AnvilScreen
    @Inject(method = "updateResult()V", cancellable = true, at = @At("HEAD"))
     public void nileUpdateResult(CallbackInfo cb) {
        AnvilScreenHandler tthis = ((AnvilScreenHandler)(Object)this);
        AnvilAccessor ttthis = (AnvilAccessor)tthis;
        ForgingAccessor tttthis = (ForgingAccessor)tthis;

        ItemStack itemStack = tttthis.getInput().getStack(0);
        ttthis.getLevelCost().set(1);
        int i = 0;
        int j = 0;
        int k = 0;
        if (itemStack.isEmpty()) {
           tttthis.getOutput().setStack(0, ItemStack.EMPTY);
           ttthis.getLevelCost().set(0);
        } else {
           ItemStack itemStack2 = itemStack.copy();
           ItemStack itemStack3 = tttthis.getInput().getStack(1);
           Map<Enchantment, Integer> map = EnchantmentHelper.get(itemStack2);
           j = j + itemStack.getRepairCost() + (itemStack3.isEmpty() ? 0 : itemStack3.getRepairCost());
           ttthis.setRepairUsage(0);
           if (!itemStack3.isEmpty()) {
              boolean bl = itemStack3.getItem() == Items.ENCHANTED_BOOK && !EnchantedBookItem.getEnchantmentNbt(itemStack3).isEmpty();
              int o;
              int p;
              int q;
              if (itemStack2.isDamageable() && itemStack2.getItem().canRepair(itemStack, itemStack3)) {
                 o = Math.min(itemStack2.getDamage(), itemStack2.getMaxDamage() / 4);
                 if (o <= 0) {
                    tttthis.getOutput().setStack(0, ItemStack.EMPTY);
                    ttthis.getLevelCost().set(0);
                    cb.cancel();
                    return;
                 }
  
                 for(p = 0; o > 0 && p < itemStack3.getCount(); ++p) {
                    q = itemStack2.getDamage() - o;
                    itemStack2.setDamage(q);
                    ++i;
                    o = Math.min(itemStack2.getDamage(), itemStack2.getMaxDamage() / 4);
                 }
  
                 ttthis.setRepairUsage(p);
              } else {
                 if (!bl && (itemStack2.getItem() != itemStack3.getItem() || !itemStack2.isDamageable())) {
                    tttthis.getOutput().setStack(0, ItemStack.EMPTY);
                    ttthis.getLevelCost().set(0);
                    cb.cancel();
                    return;
                 }
  
                 if (itemStack2.isDamageable() && !bl) {
                    o = itemStack.getMaxDamage() - itemStack.getDamage();
                    p = itemStack3.getMaxDamage() - itemStack3.getDamage();
                    q = p + itemStack2.getMaxDamage() * 12 / 100;
                    int r = o + q;
                    int s = itemStack2.getMaxDamage() - r;
                    if (s < 0) {
                       s = 0;
                    }
  
                    if (s < itemStack2.getDamage()) {
                       itemStack2.setDamage(s);
                       i += 2;
                    }
                 }
  
                 Map<Enchantment, Integer> map2 = EnchantmentHelper.get(itemStack3);
                 boolean bl2 = false;
                 boolean bl3 = false;
                 Iterator var24 = map2.keySet().iterator();
  
                 label155:
                 while(true) {
                    Enchantment enchantment;
                    do {
                       if (!var24.hasNext()) {
                          if (bl3 && !bl2) {
                             tttthis.getOutput().setStack(0, ItemStack.EMPTY);
                             ttthis.getLevelCost().set(0);
                             cb.cancel();
                            return;
                          }
                          break label155;
                       }
  
                       enchantment = (Enchantment)var24.next();
                    } while(enchantment == null);
  
                    int t = (Integer)map.getOrDefault(enchantment, 0);
                    int u = (Integer)map2.get(enchantment);
                    u = t == u ? u + 1 : Math.max(u, t);
                    boolean bl4 = enchantment.isAcceptableItem(itemStack);
                    if (tttthis.getPlayer().getAbilities().creativeMode || itemStack.getItem() == Items.ENCHANTED_BOOK) {
                       bl4 = true;
                    }
  
                    Iterator var17 = map.keySet().iterator();
  
                    while(var17.hasNext()) {
                       Enchantment enchantment2 = (Enchantment)var17.next();
                       if (enchantment2 != enchantment && !enchantment.canCombine(enchantment2)) {
                          bl4 = false;
                          ++i;
                       }
                    }
  
                    if (!bl4) {
                       bl3 = true;
                    } else {
                       bl2 = true;
                       if (u > enchantment.getMaxLevel()) {
                          u = enchantment.getMaxLevel();
                       }
  
                       map.put(enchantment, u);
                       int v = 0;
                       switch(enchantment.getRarity()) {
                       case COMMON:
                          v = 1;
                          break;
                       case UNCOMMON:
                          v = 2;
                          break;
                       case RARE:
                          v = 4;
                          break;
                       case VERY_RARE:
                          v = 8;
                       }
  
                       if (bl) {
                          v = Math.max(1, v / 2);
                       }
  
                       i += v * u;
                       if (itemStack.getCount() > 1) {
                          i = Integer.MAX_VALUE;
                       }
                    }
                 }
              }
           }
  
           if (StringUtils.isBlank(ttthis.getNewItemName())) {
              if (itemStack.hasCustomName()) {
                 k = 1;
                 i += k;
                 itemStack2.removeCustomName();
              }
           } else if (!ttthis.getNewItemName().equals(itemStack.getName().getString())) {
              k = 1;
              i += k;
              itemStack2.setCustomName(new LiteralText(ttthis.getNewItemName()));
           }
  
           ttthis.getLevelCost().set(j + i);
           if (i <= 0) {
              itemStack2 = ItemStack.EMPTY;
           }
  
           if (k == i && k > 0 && ttthis.getLevelCost().get() >= 40) {
              ttthis.getLevelCost().set(39);
           }
  
        //    if (ttthis.getLevelCost().get() >= 40 && !tttthis.getPlayer().abilities.creativeMode) {
        //       itemStack2 = ItemStack.EMPTY;
        //    }
  
           if (!itemStack2.isEmpty()) {
              int w = itemStack2.getRepairCost();
              if (!itemStack3.isEmpty() && w < itemStack3.getRepairCost()) {
                 w = itemStack3.getRepairCost();
              }
  
              if (k != i || k == 0) {
                 w = AnvilScreenHandler.getNextCost(w);
              }
  
              itemStack2.setRepairCost(w);
              EnchantmentHelper.set(map, itemStack2);
           }
  
           tttthis.getOutput().setStack(0, itemStack2);
           tthis.sendContentUpdates();
           cb.cancel();
        }
     }
    
}
