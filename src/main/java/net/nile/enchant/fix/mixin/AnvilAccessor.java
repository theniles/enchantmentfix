package net.nile.enchant.fix.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.Property;

@Mixin(AnvilScreenHandler.class)
public interface AnvilAccessor {
    @Accessor("levelCost")
    public Property getLevelCost();

    @Accessor("newItemName")
    public String getNewItemName();

    @Accessor("repairItemUsage")
    public void setRepairUsage(int usage);
}
