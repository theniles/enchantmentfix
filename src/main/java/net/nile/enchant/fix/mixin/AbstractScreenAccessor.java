package net.nile.enchant.fix.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

@Mixin(Screen.class)
public interface AbstractScreenAccessor {
    @Accessor("client")
    public MinecraftClient getClient();

    @Accessor("textRenderer")
    public TextRenderer getTextRenderer();

    @Accessor("title")
    public Text getTitle();
}
